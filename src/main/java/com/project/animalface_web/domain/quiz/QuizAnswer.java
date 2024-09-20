package com.project.animalface_web.domain.quiz;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizAnswerNo;

    private String correctAnswer;

    @OneToOne
    @JoinColumn(name = "quiz_question_no")
    private QuizQuestion question;


}