package com.quiz.model.dto;


import java.util.Date;

public record UserQuizDTO(Long userId, Long quizId, int score, Date assignedDate, Date completedDate) {
}