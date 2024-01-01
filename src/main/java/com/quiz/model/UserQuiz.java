package com.quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity(name="app_user_quiz")
@Data
public class UserQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Quiz quiz;

    @Column(name = "true_answer_count")
    private int trueAnswerCount;

    @Column(name = "wrong_answer_count")
    private int wrongAnswerCount;

    private Date assignedDate;

    private Date completedDate;
}