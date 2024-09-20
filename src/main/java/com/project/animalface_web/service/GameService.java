package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.repository.GameRepository;
import com.project.animalface_web.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long gameNo);
    Game saveGame(Game game);
    void deleteGame(Long gameNo);

    List<GameQuestion> getQuestionsByGameId(Long gameNo);
    List<GameAnswer> getAnswersByQuestionId(Long questionNo);
}


