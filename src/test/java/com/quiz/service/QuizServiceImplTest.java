package com.quiz.service;

import com.quiz.model.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.serviceImpl.QuizServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizServiceImplTest {

    @Mock
    private QuizRepository repository;

    @InjectMocks
    private QuizServiceImpl quizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveQuiz() {
        Quiz quizToSave = new Quiz();
        when(repository.save(quizToSave)).thenReturn(quizToSave);

        Quiz savedQuiz = quizService.save(quizToSave);

        assertNotNull(savedQuiz);
        verify(repository, times(1)).save(quizToSave);
    }

    @Test
    void findById() {
        long quizId = 1L;
        Quiz expectedQuiz = new Quiz();
        when(repository.findById(quizId)).thenReturn(Optional.of(expectedQuiz));

        Optional<Quiz> quizOptional = quizService.findById(quizId);

        assertTrue(quizOptional.isPresent());
        assertEquals(expectedQuiz, quizOptional.get());
        verify(repository, times(1)).findById(quizId);
    }

    @Test
    void updateQuiz() {
        long quizId = 1L;
        Quiz existingQuiz = new Quiz();
        Quiz updatedQuiz = new Quiz();

        when(repository.findById(quizId)).thenReturn(Optional.of(existingQuiz));
        when(repository.save(existingQuiz)).thenReturn(updatedQuiz);

        Quiz result = quizService.update(updatedQuiz, quizId);

        assertNotNull(result);
        assertEquals(updatedQuiz, result);
        verify(repository, times(1)).findById(quizId);
        verify(repository, times(1)).save(existingQuiz);
    }

    @Test
    void getAllQuizzes() {
        List<Quiz> expectedQuizzes = Arrays.asList(new Quiz(), new Quiz());
        when(repository.findAll()).thenReturn(expectedQuizzes);

        List<Quiz> quizzes = quizService.getAll();

        assertNotNull(quizzes);
        assertEquals(expectedQuizzes.size(), quizzes.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void saveAllQuizzes() {
        List<Quiz> quizzesToSave = Arrays.asList(new Quiz(), new Quiz());
        when(repository.saveAll(quizzesToSave)).thenReturn(quizzesToSave);

        List<Quiz> savedQuizzes = quizService.saveAll(quizzesToSave);

        assertNotNull(savedQuizzes);
        assertEquals(quizzesToSave.size(), savedQuizzes.size());
        verify(repository, times(1)).saveAll(quizzesToSave);
    }
}
