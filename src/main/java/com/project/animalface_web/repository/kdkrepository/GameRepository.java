package com.project.animalface_web.repository.kdkrepository;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
