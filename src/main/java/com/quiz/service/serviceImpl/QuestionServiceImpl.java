package com.quiz.service.serviceImpl;

import com.quiz.model.Option;
import com.quiz.model.OptionQuestion;
import com.quiz.model.Question;
import com.quiz.model.dto.QuestionDTO;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.OptionQuestionService;
import com.quiz.service.OptionService;
import com.quiz.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;
    private final OptionService optionService;
    private final OptionQuestionService optionQuestionService;


    @Override
    public Question save(QuestionDTO entity) {
        return null;
    }

    @Override
    public Optional<Question> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Question> saveAll(List<QuestionDTO> questionDTOS) {
        return questionDTOS.stream()
                .map(this::saveQuestion)
                .collect(Collectors.toList());
    }

    public Question saveQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTitle(questionDTO.title());

        Question finalQuestion1 = question;
        List<Option> options = questionDTO.optionIds().stream()
                .map(optionId -> {
                    Option option = optionService.findById(optionId)
                            .orElseThrow(() -> new IllegalArgumentException("Option not found with id: " + optionId));

                    option.setQuestion(finalQuestion1);
                    return option;
                })
                .collect(Collectors.toList());
        question.setCorrectOption(optionService.findById(questionDTO.correctOptionId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Option not found with id: " + questionDTO.correctOptionId())));
        question.setOptions(options);

        question = repository.saveAndFlush(question);

        Question finalQuestion = question;
        List<OptionQuestion> optionQuestions = options.stream()
                .map(option -> {
                    OptionQuestion optionQuestion = new OptionQuestion();
                    optionQuestion.setQuestion(finalQuestion);
                    optionQuestion.setOption(option);
                    return optionQuestion;
                })
                .collect(Collectors.toList());

        optionQuestionService.saveAll(optionQuestions);

        return question;
    }

    @Override
    public List<Question> getAll() {
        return repository.findAll();
    }
}
