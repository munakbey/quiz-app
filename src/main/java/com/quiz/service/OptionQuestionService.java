package com.quiz.service;

import com.quiz.model.OptionQuestion;
import com.quiz.model.dto.OptionQuestionDTO;

import java.util.List;

public interface OptionQuestionService extends BaseService<OptionQuestion, OptionQuestionDTO> {
    List<OptionQuestion> saveAll(List<OptionQuestion> optionQuestions);
}
