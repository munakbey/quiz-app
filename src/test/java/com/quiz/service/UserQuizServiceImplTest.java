package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserQuizDTO;
import com.quiz.repository.UserQuizRepository;
import com.quiz.service.serviceImpl.UserQuizServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserQuizServiceImplTest {

    @Mock
    private UserQuizRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private UserQuizServiceImpl userQuizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignQuizToUsers() {
        // Test data
        UserQuizDTO userQuizDTO1 = new UserQuizDTO(1L, 1L, 0, null, null);
        UserQuizDTO userQuizDTO2 = new UserQuizDTO(2L, 1L, 0, null, null);
        List<UserQuizDTO> userQuizDTOList = Arrays.asList(userQuizDTO1, userQuizDTO2);

        User user1 = new User();
        User user2 = new User();
        when(userService.findById(anyLong())).thenReturn(Optional.of(user1), Optional.of(user2));

        Quiz quiz = new Quiz();
        when(quizService.findById(anyLong())).thenReturn(Optional.of(quiz));

        // Invoke the method
        userQuizService.assignQuizToUsers(userQuizDTOList);

        // Verify interactions and save method invocation
        verify(userService, times(2)).findById(anyLong());
        verify(quizService, times(2)).findById(anyLong());
        verify(repository, times(2)).save(any(UserQuiz.class));
    }

    @Test
    void testIsCompleted() {
        // Test data
        Long userId = 1L;
        Long quizId = 2L;

        // Mock repository response
        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setCompletedDate(new Date());
        when(repository.findByUserIdAndQuizId(anyLong(), anyLong())).thenReturn(userQuiz);

        // Invoke the method
        boolean result = userQuizService.isComplated(userId, quizId);

        // Verify the result
        assertTrue(result);
    }

    @Test
    void testIsNotCompleted() {
        // Test data
        Long userId = 1L;
        Long quizId = 2L;

        // Mock repository response
        UserQuiz userQuiz = new UserQuiz();
        when(repository.findByUserIdAndQuizId(anyLong(), anyLong())).thenReturn(userQuiz);

        // Invoke the method
        boolean result = userQuizService.isComplated(userId, quizId);

        // Verify the result
        assertFalse(result);
    }
}
