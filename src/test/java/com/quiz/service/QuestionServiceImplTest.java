package com.quiz.service;

import com.quiz.model.Option;
import com.quiz.model.Question;
import com.quiz.model.dto.QuestionDTO;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.serviceImpl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {
    @Mock
    private QuestionRepository repository;

    @Mock
    private OptionService optionService;

    @Mock
    private OptionQuestionService optionQuestionService;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveQuestion() {
        QuestionDTO questionDTO = createMockQuestionDTO();
        Question question = createMockQuestion();

        when(optionService.findById(anyLong())).thenReturn(Optional.of(createMockOption()));
        when(repository.saveAndFlush(any(Question.class))).thenReturn(question);

        // Test
        Question savedQuestion = questionService.saveQuestion(questionDTO);

        assertNotNull(savedQuestion);
        assertEquals(question, savedQuestion);
        verify(repository, times(1)).saveAndFlush(any(Question.class));
    }

    @Test
    void saveAllQuestions() {
        // Mocking data
        List<QuestionDTO> questionDTOs = Arrays.asList(createMockQuestionDTO(), createMockQuestionDTO());
        Question question = createMockQuestion();

        when(optionService.findById(anyLong())).thenReturn(Optional.of(createMockOption()));
        when(repository.saveAndFlush(any(Question.class))).thenReturn(question);

        // Test
        List<Question> savedQuestions = questionService.saveAll(questionDTOs);

        assertNotNull(savedQuestions);
        assertEquals(questionDTOs.size(), savedQuestions.size());
        verify(repository, times(questionDTOs.size())).saveAndFlush(any(Question.class));
    }

    // Helper methods to create mock objects for testing
    private QuestionDTO createMockQuestionDTO() {
        return new QuestionDTO("Question Title", Set.of(), 1L);
    }

    private Question createMockQuestion() {
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();

        Option option1 = Option.builder()
                .content("Berlin")
                .question(question)
                .build();

        Option correctOption = Option.builder()
                .content("Paris") // Correcting the content
                .question(question)
                .build();

        return  Question.builder()
                .title("Question Title")
                .options(List.of(option1, correctOption))
                .correctOption(correctOption)
                .build();
    }

    private Option createMockOption() {
        return Option.builder()
                .content("Berlin")
                .question(Question.builder().title("Sample Question").build())
                .build();
    }
}
