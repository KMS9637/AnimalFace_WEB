package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.repository.GameAnswerRepository;
import com.project.animalface_web.repository.GameQuestionRepository;
import com.project.animalface_web.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameQuestionRepository gameQuestionRepository;
    private final GameAnswerRepository gameAnswerRepository;

    public GameServiceImpl(GameRepository gameRepository, GameQuestionRepository gameQuestionRepository, GameAnswerRepository gameAnswerRepository) {
        this.gameRepository = gameRepository;
        this.gameQuestionRepository = gameQuestionRepository;
        this.gameAnswerRepository = gameAnswerRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long gameNo) {
        return gameRepository.findById(gameNo)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long gameNo) {
        gameRepository.deleteById(gameNo);
    }

    @Override
    public List<GameQuestion> getQuestionsByGameId(Long gameNo) {
        return gameQuestionRepository.findByGame_GameNo(gameNo);
    }

    @Override
    public List<GameAnswer> getAnswersByQuestionId(Long questionNo) {
        return gameAnswerRepository.findByQuestion_QuestionNo(questionNo);
    }
}

