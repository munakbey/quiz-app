package com.quiz.model.dto;

import java.util.Set;

public record QuestionDTO(String title, Set<Long> optionIds, Long correctOptionId) {
}
