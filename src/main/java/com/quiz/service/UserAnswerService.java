package com.quiz.service;

import com.quiz.model.UserAnswer;
import com.quiz.model.dto.UserAnswerDTO;
import com.quiz.model.response.ScoreResponse;

import java.util.List;

public interface UserAnswerService extends BaseService<UserAnswer, UserAnswerDTO>{
    void complateUserQuiz(Long userQuizId);
    List<UserAnswer> getAnsweredQuestions(Long userQuizId);
    ScoreResponse getScore(Long userQuizId);
}
