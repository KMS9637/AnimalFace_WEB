package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.repository.GameRepository;
import com.project.animalface_web.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game startGame(Long gameId);
    GameResult calculateResult(List<Long> answerIds);
    Game saveGameWithQuestionsAnswersAndResults(Game game);
    List<Game> getAllGames();
}