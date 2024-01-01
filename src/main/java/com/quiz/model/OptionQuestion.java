package com.quiz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id")
    @NonNull
    private Option option;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @NonNull
    private Question question;

    @NonNull
    private Boolean isCorrectOption;
}