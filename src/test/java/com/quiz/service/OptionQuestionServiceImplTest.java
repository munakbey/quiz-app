package com.quiz.service;
import static org.mockito.Mockito.*;

import com.quiz.model.OptionQuestion;
import com.quiz.model.dto.OptionQuestionDTO;
import com.quiz.repository.OptionQuestionRepository;
import com.quiz.service.serviceImpl.OptionQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class OptionQuestionServiceImplTest {

    private OptionQuestionRepository repository;
    private OptionQuestionService service;

    @BeforeEach
    void setUp() {
        repository = mock(OptionQuestionRepository.class);
        service = new OptionQuestionServiceImpl(repository);
    }

    @Test
    void save() {
        List<Long> questionIds = Arrays.asList(1L, 2L, 3L); // Örnek questionIds listesi
        List<Long> optionIds = Arrays.asList(4L, 5L, 6L);    // Örnek optionIds listesi

        OptionQuestionDTO optionQuestionDTO = new OptionQuestionDTO(questionIds, optionIds);
        // Mock repository response
        when(repository.save(any(OptionQuestion.class))).thenReturn(new OptionQuestion());

        OptionQuestion result = service.save(optionQuestionDTO);

        // Verify that the save method of the repository is called
        verify(repository, times(1)).save(any(OptionQuestion.class));

    }

    @Test
    void findById() {
        long optionQuestionId = 1L;

        // Mock repository response
        when(repository.findById(optionQuestionId)).thenReturn(Optional.of(new OptionQuestion()));

        Optional<OptionQuestion> result = service.findById(optionQuestionId);

        // Verify that the findById method of the repository is called
        verify(repository, times(1)).findById(optionQuestionId);
    }

    @Test
    void saveAll() {
        List<OptionQuestion> optionQuestions = new ArrayList<>();

        // Mock repository response
        when(repository.saveAll(optionQuestions)).thenReturn(optionQuestions);

        List<OptionQuestion> result = service.saveAll(optionQuestions);

        // Verify that the saveAll method of the repository is called
        verify(repository, times(1)).saveAll(optionQuestions);
    }
}
