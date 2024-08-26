package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

}
