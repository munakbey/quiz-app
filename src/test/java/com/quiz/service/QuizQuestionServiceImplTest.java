package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.model.QuizQuestion;
import com.quiz.model.dto.QuizQuestionDTO;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.service.QuestionService;
import com.quiz.service.QuizQuestionService;
import com.quiz.service.QuizService;
import com.quiz.service.serviceImpl.QuizQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class QuizQuestionServiceImplTest {

    @Mock
    private QuizQuestionRepository repository;

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuizQuestionServiceImpl quizQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveQuizQuestion() {
        long quizId = 1L;
        List<Long> questionIds = Arrays.asList(2L, 3L);

        QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO(quizId, questionIds);
        Quiz quiz = new Quiz();
        List<Question> questions = Arrays.asList(new Question(), new Question());

        when(quizService.findById(anyLong())).thenReturn(Optional.of(quiz));
        when(questionService.findById(anyLong())).thenReturn(Optional.of(new Question()));

        when(repository.save(any(QuizQuestion.class))).thenAnswer(invocation -> {
            QuizQuestion savedQuizQuestion = invocation.getArgument(0);
            savedQuizQuestion.setId(1L); // Set an ID for the saved entity
            return savedQuizQuestion;
        });

        QuizQuestion savedQuizQuestion = quizQuestionService.save(quizQuestionDTO);

        assertEquals(quiz, savedQuizQuestion.getQuiz());
        assertEquals(questions.size(), savedQuizQuestion.getQuestions().size());
        verify(quizService, times(1)).findById(anyLong());
        verify(questionService, times(questionIds.size())).findById(anyLong());
        verify(repository, times(1)).save(any(QuizQuestion.class));
    }

    @Test
    void testSaveQuizQuestionWithNonExistingQuiz() {
        long nonExistingQuizId = 999L;
        List<Long> questionIds = Collections.singletonList(1L);

        QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO(nonExistingQuizId, questionIds);

        when(quizService.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> quizQuestionService.save(quizQuestionDTO));

        assertEquals("Quiz not found with id: " + nonExistingQuizId, exception.getMessage());
        verify(quizService, times(1)).findById(anyLong());
        verify(questionService, never()).findById(anyLong());
        verify(repository, never()).save(any(QuizQuestion.class));
    }
}
