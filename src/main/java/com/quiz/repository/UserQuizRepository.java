package com.quiz.repository;

import com.quiz.model.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
    UserQuiz findByUserIdAndQuizId(Long userId, Long quizId);
}
