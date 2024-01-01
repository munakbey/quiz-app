package com.quiz.service;

import com.quiz.model.Quiz;

import java.util.List;

public interface QuizService extends BaseService<Quiz,Quiz> {
    List<Quiz> saveAll(List<Quiz> quizs);
    List<Quiz> getAll();
    Quiz update(Quiz entity, long id);
}
