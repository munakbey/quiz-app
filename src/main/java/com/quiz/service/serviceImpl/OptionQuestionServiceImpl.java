package com.quiz.service.serviceImpl;

import com.quiz.model.OptionQuestion;
import com.quiz.model.dto.OptionQuestionDTO;
import com.quiz.repository.OptionQuestionRepository;
import com.quiz.service.OptionQuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OptionQuestionServiceImpl implements OptionQuestionService {

    private final OptionQuestionRepository repository;

    @Override
    public OptionQuestion save(OptionQuestionDTO entity) {
        return null;
    }

    @Override
    public Optional<OptionQuestion> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<OptionQuestion> saveAll(List<OptionQuestion> optionQuestions) {
        return repository.saveAll(optionQuestions);
    }
}
