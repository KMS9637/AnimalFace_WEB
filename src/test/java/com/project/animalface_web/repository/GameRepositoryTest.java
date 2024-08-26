package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.domain.GameResult;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameAnswerRepository gameAnswerRepository;

    @Autowired
    GameQuestionRepository gameQuestionRepository;

    @Autowired
    GameResultRepository gameResultRepository;

    @Test
    public void GameQuestionInsertTest(){
        // Game 객체 생성 및 저장
        Game game = Game.builder()
                .gameName("1번 게임")
                .build();

        Game savedGame = gameRepository.save(game);
        log.info("추가한 게임 번호 : " + savedGame.getGameNo());

        // GameQuestion 저장
        IntStream.rangeClosed(1, 10).forEach(i -> {
            GameQuestion gameQuestion = GameQuestion.builder()
                    .game(savedGame)
                    .gameQuestion("1번 질문 " + i)
                    .build();

            GameQuestion savedGameQuestion = gameQuestionRepository.save(gameQuestion);
            log.info("저장된 GameQuestion 번호 : " + savedGameQuestion.getQuestionId());
        });

        // GameAnswer 저장
        IntStream.rangeClosed(1, 10).forEach(i -> {
            GameAnswer gameAnswer = GameAnswer.builder()
                    .game(savedGame)
                    .gameAnswer("1번 질문의 답변 " + i)
                    .build();

            GameAnswer savedGameAnswer = gameAnswerRepository.save(gameAnswer);
            log.info("저장된 GameAnswer 번호 : " + savedGameAnswer.getAnswerId());
        });

        // GameResult 저장
        IntStream.rangeClosed(1, 10).forEach(i -> {
            GameResult gameResult = GameResult.builder()
                    .game(savedGame)
                    .gameResult("1번 게임의 결과 " + i)
                    .build();

            GameResult savedGameResult = gameResultRepository.save(gameResult);
            log.info("저장된 GameResult 번호 : " + savedGameResult.getResultId());
        });
    }
}
