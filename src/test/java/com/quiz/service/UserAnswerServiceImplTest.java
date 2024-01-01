package com.quiz.service;

import com.quiz.model.Option;
import com.quiz.model.Question;
import com.quiz.model.UserAnswer;
import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserAnswerDTO;
import com.quiz.model.response.ScoreResponse;
import com.quiz.repository.UserAnswerRepository;
import com.quiz.service.serviceImpl.UserAnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserAnswerServiceImplTest {

    @Mock
    private UserAnswerRepository repository;

    @Mock
    private UserQuizService userQuizService;

    @Mock
    private QuestionService questionService;

    @Mock
    private OptionService optionService;

    @InjectMocks
    private UserAnswerServiceImpl userAnswerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserAnswer() {
        // Test data
        UserAnswerDTO userAnswerDTO = new UserAnswerDTO(1L, 1L, 1L);

        UserQuiz userQuiz = new UserQuiz();
        when(userQuizService.findById(anyLong())).thenReturn(Optional.of(userQuiz));

        Question question = new Question();
        // Ensure that the Question object has a non-null correctOption
        Option correctOption = new Option();
        correctOption.setId(1L); // Set the appropriate ID
        question.setCorrectOption(correctOption);
        when(questionService.findById(anyLong())).thenReturn(Optional.of(question));

        when(optionService.findById(anyLong())).thenReturn(Optional.ofNullable(null)); // Adjust as needed

        when(repository.save(any(UserAnswer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Invoke the method
        UserAnswer savedUserAnswer = userAnswerService.save(userAnswerDTO);

        // Verify interactions and save method invocation
        assertNotNull(savedUserAnswer);
        assertEquals(userQuiz, savedUserAnswer.getUserQuiz());
        assertEquals(question, savedUserAnswer.getQuestion());
        verify(repository, times(1)).save(any(UserAnswer.class));
    }

    @Test
    void testCompleteUserQuiz() {
        // Test data
        Long userQuizId = 1L;

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserQuiz(new UserQuiz());

        List<UserAnswer> userAnswerList = Collections.singletonList(userAnswer);
        when(repository.findByUserQuizId(anyLong())).thenReturn(userAnswerList);

        UserQuiz userQuiz = new UserQuiz();
        when(userQuizService.findById(anyLong())).thenReturn(Optional.of(userQuiz));

        // Verify interactions and update method invocation
        assertNotNull(userQuiz.getTrueAnswerCount());
        assertNotNull(userQuiz.getWrongAnswerCount());
    }

    @Test
    void testGetAnsweredQuestions() {
        // Test data
        Long userQuizId = 1L;

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserQuiz(new UserQuiz());
        when(repository.findByUserQuizIdAndSelectedOptionIsNotNull(anyLong())).thenReturn(Collections.singletonList(userAnswer));

        // Invoke the method
        List<UserAnswer> answeredQuestions = userAnswerService.getAnsweredQuestions(userQuizId);

        // Verify the result
        assertNotNull(answeredQuestions);
        assertEquals(1, answeredQuestions.size());
        verify(repository, times(1)).findByUserQuizIdAndSelectedOptionIsNotNull(anyLong());
    }

    @Test
    void testGetScore() {
        // Test data
        Long userQuizId = 1L;

        UserAnswer userAnswer1 = new UserAnswer();
        UserAnswer userAnswer2 = new UserAnswer();
        List<UserAnswer> userAnswers = List.of(userAnswer1, userAnswer2);
        when(repository.findByUserQuizId(anyLong())).thenReturn(userAnswers);

        // Invoke the method
        ScoreResponse scoreResponse = userAnswerService.getScore(userQuizId);

        // Verify the result
        assertNotNull(scoreResponse);
    }
}
