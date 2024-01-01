package com.quiz.service;

import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserQuizDTO;

import java.util.List;

public interface UserQuizService extends BaseService<UserQuiz, UserQuizDTO> {
    void assignQuizToUsers(List<UserQuizDTO> userQuizDTOList);
    void save(UserQuiz userQuiz);
    List<UserQuiz> getAll();

    Boolean isComplated(Long userId, Long quizId);
}
