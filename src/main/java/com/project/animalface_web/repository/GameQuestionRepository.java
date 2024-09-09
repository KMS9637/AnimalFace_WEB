package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameQuestionRepository extends JpaRepository<GameQuestion, Long> {

}
