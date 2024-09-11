package com.project.animalface_web.repository;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
}
