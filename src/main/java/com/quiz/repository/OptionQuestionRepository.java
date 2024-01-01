package com.quiz.repository;

import com.quiz.model.OptionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionQuestionRepository extends JpaRepository<OptionQuestion, Long> {
}
