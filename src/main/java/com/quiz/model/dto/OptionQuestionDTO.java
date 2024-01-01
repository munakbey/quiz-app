package com.quiz.model.dto;

import java.util.List;

public record OptionQuestionDTO(List<Long> questionIds, List<Long> optionIds) { }
