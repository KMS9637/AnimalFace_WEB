package com.project.animalface_web.repository.kdkrepository;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreateGameRepository extends JpaRepository<CreateGame, Long> {


}
