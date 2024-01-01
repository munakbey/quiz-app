package com.quiz.repository;

import com.quiz.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserQuizId(Long userQuizId);
    List<UserAnswer> findByUserQuizIdAndSelectedOptionIsNotNull(Long userQuizId);

}
