package com.quiz.model.dto;

public record UserAnswerDTO(Long userQuizId,Long questionId, Long selectedOptionId) {
}
