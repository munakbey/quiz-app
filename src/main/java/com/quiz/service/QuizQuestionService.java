package com.quiz.service;

import com.quiz.model.QuizQuestion;
import com.quiz.model.dto.QuizQuestionDTO;

import java.util.List;

public interface QuizQuestionService extends BaseService<QuizQuestion, QuizQuestionDTO> {
    List<QuizQuestion> getAll();
}
