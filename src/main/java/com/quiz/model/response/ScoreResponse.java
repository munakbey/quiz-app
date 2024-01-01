package com.quiz.model.response;

import lombok.Builder;

public record ScoreResponse(Integer trueAnswerCount, Integer wrongAnswerCount, Integer notAnsweredCount) {
}
