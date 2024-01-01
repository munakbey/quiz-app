package com.quiz.service.serviceImpl;

import com.quiz.model.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    @Override
    public Quiz save(Quiz entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Quiz> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Quiz update(Quiz entity, long id) {
        return repository.findById(id).map(item -> {
            item.setCreatedDate(entity.getCreatedDate());
            item.setPassScore(entity.getPassScore());

            return repository.save(item);
        }).orElseThrow();
    }
    @Override
    public List<Quiz> saveAll(List<Quiz> quizs) {
        return repository.saveAll(quizs);
    }

    @Override
    public List<Quiz> getAll() {
        return repository.findAll();
    }

}
