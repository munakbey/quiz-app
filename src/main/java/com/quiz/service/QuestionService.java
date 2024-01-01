package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.dto.QuestionDTO;

import java.util.List;

public interface QuestionService extends BaseService<Question,QuestionDTO> {
    List<Question> saveAll(List<QuestionDTO> questionDTOS);
    List<Question> getAll();
}
