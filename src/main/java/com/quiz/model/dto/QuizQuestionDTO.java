package com.quiz.model.dto;

import java.util.List;

public record QuizQuestionDTO(Long quizId, List<Long> questionIds) {
}
