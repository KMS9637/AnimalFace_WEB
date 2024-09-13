package com.project.animalface_web.service;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.repository.GameAnswerRepository;
import com.project.animalface_web.repository.GameQuestionRepository;
import com.project.animalface_web.repository.GameRepository;
import com.project.animalface_web.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameAnswerRepository gameAnswerRepository;
    private final GameQuestionRepository gameQuestionRepository;
    private final GameResultRepository gameResultRepository;

    @Override
    public Game startGame(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game ID: " + gameId));
    }

    private int findAnswerScoreById(Long answerId) {
        return gameRepository.findAnswerScoreById(answerId)
                .orElse(0);  // 기본값 0으로 처리
    }

    @Override
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

    @Override
    public Game saveGameWithQuestionsAnswersAndResults(Game game) {
        Game savedGame = gameRepository.save(game);

        List<GameQuestion> gameQuestions = new ArrayList<>();

        // 질문과 답변 생성 및 저장
        IntStream.rangeClosed(1, 10).forEach(i -> {
            GameQuestion gameQuestion = GameQuestion.builder()
                    .game(savedGame)
                    .questionText("Sample Question " + i)
                    .build();
            GameQuestion savedGameQuestion = gameQuestionRepository.save(gameQuestion);

            // 이지선다형 답변을 리스트에 저장
            List<GameAnswer> answers = new ArrayList<>();
            answers.add(GameAnswer.builder()
                    .question(savedGameQuestion)
                    .answerText("Sample Answer 1 for Question " + i)
                    .score(0)
                    .build());
            answers.add(GameAnswer.builder()
                    .question(savedGameQuestion)
                    .answerText("Sample Answer 2 for Question " + i)
                    .score(1)
                    .build());

            // 답변 순서를 무작위로 섞음
            Collections.shuffle(answers);

            // 섞인 답변을 저장
            answers.forEach(answer -> {
                gameAnswerRepository.save(answer);
                savedGameQuestion.getAnswers().add(answer);
            });

            gameQuestions.add(savedGameQuestion);
        });

        savedGame.setQuestions(gameQuestions);
        gameRepository.save(savedGame);

        // 결과 저장
        int intervalSize = 8;
        IntStream.rangeClosed(0, 4).forEach(i -> {
            int minScore = i * intervalSize;
            int maxScore = minScore + intervalSize - 1;
            if (i == 4) maxScore = 40;

            GameResult gameResult = GameResult.builder()
                    .resultText("Sample Result " + (i + 1))
                    .minScore(minScore)
                    .maxScore(maxScore)
                    .build();
            gameResultRepository.save(gameResult);
        });

        return savedGame;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}