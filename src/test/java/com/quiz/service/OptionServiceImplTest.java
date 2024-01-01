package com.quiz.service;

import com.quiz.model.Option;
import com.quiz.model.Question;
import com.quiz.repository.OptionRepository;
import com.quiz.service.serviceImpl.OptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OptionServiceImplTest {

    @Mock
    private OptionRepository repository;

    @InjectMocks
    private OptionServiceImpl optionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOption() {
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();
        Option optionToSave = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        when(repository.save(any(Option.class))).thenReturn(optionToSave);

        Option savedOption = optionService.save(optionToSave);

        assertEquals(optionToSave.getContent(), savedOption.getContent());
        verify(repository, times(1)).save(any(Option.class));
    }

    @Test
    void testFindOptionById() {
        long optionId = 1L;
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();
        Option option = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        when(repository.findById(anyLong())).thenReturn(Optional.of(option));

        Optional<Option> foundOption = optionService.findById(optionId);

        assertTrue(foundOption.isPresent());
        assertEquals(option.getContent(), foundOption.get().getContent());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateOption() {
        long optionId = 1L;
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();
        Option existingOption = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        Option updatedOption = Option.builder()
                .content("Istanbul")
                .question(question)
                .build();;
        when(repository.findById(anyLong())).thenReturn(Optional.of(existingOption));
        when(repository.save(any(Option.class))).thenReturn(updatedOption);

        Option result = optionService.update(updatedOption, optionId);

        assertEquals(updatedOption.getContent(), result.getContent());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Option.class));
    }

    @Test
    void testGetAllOptions() {
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();

        Option option1 = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        Option option2 = Option.builder()
                .content("Istanbul")
                .question(question)
                .build();;
        List<Option> options = Arrays.asList(option1, option2);
        when(repository.findAll()).thenReturn(options);

        List<Option> result = optionService.getAll();

        assertEquals(options.size(), result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveAllOptions() {
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();
        Option option1 = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        Option option2 = Option.builder()
                .content("Istanbul")
                .question(question)
                .build();;
        List<Option> optionsToSave = Arrays.asList(option1, option2);
        when(repository.saveAll(anyIterable())).thenReturn(optionsToSave);

        List<Option> savedOptions = optionService.saveAll(optionsToSave);

        assertEquals(optionsToSave.size(), savedOptions.size());
        verify(repository, times(1)).saveAll(anyIterable());
    }

    @Test
    void testGetAllOptionsById() {
        Set<Long> optionIds = Set.of(1L, 2L);
        Question question = Question.builder()
                .title("What is the capital of France?")
                .build();
        Option option1 = Option.builder()
                .content("Berlin")
                .question(question)
                .build();;
        Option option2 = Option.builder()
                .content("Istanbul")
                .question(question)
                .build();;
        List<Option> options = Arrays.asList(option1,option2);
        when(repository.findAllById(anyIterable())).thenReturn(options);

        Set<Option> result = optionService.getAllById(optionIds);

        assertEquals(options.size(), result.size());
        verify(repository, times(1)).findAllById(anyIterable());
    }
}
