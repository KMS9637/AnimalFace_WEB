package com.project.animalface_web.service;

import com.project.animalface_web.domain.game.Game;
import com.project.animalface_web.domain.game.GameResult;
import com.project.animalface_web.repository.GameRepository;
import com.project.animalface_web.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameResultRepository gameResultRepository;

    public Game startGame(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game ID: " + gameId));
    }

    private int findAnswerScoreById(Long answerId) {
        return gameRepository.findAnswerScoreById(answerId)
                .orElse(0);  // 기본값 0으로 처리
    }

    public GameResult calculateResult(List<Long> answerIds) {
        int score = answerIds.stream()
                .mapToInt(this::findAnswerScoreById)
                .sum();

        if (score < 0 || score > 40) {
            throw new IllegalArgumentException("Score is out of range: " + score);
        }

        return gameResultRepository.findAll().stream()
                .filter(result -> score >= result.getMinScore() && score <= result.getMaxScore())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No result found for the given score: " + score));
    }



}

