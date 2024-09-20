package com.project.animalface_web.repository;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    Optional<QuizAnswer> findByQuestion_QuizQuestionNo(Long quizQuestionNo);
}
