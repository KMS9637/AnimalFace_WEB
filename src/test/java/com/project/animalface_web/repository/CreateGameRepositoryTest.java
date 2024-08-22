package com.project.animalface_web.repository;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.CreateGameQuestion;
import com.project.animalface_web.repository.kdkrepository.CreateGameRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CreateGameRepositoryTest {

    @Autowired
    CreateGameRepository createGameRepository;

    @Test
    public void createGameQuestionInsert(){
        IntStream.rangeClosed(1, 30).forEach(i -> {
            CreateGameQuestion createGameQuestion = CreateGameQuestion.builder()
                    .createQuestion("퀴즈 질문 더미." + i)
                    .build();

            CreateGameQuestion result = createGameQuestionRepository.save(createGameQuestion);
            log.info("추가한 createGameQuestionNo: " + result.getCreateGameNo());
        });
    }


    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,30).forEach(i ->
                {
                    CreateGame createGame = CreateGame.builder()
                            .createGameName("퀴즈 제목 더미데이터" + i)
                            .createGameQuestions()
                            .build();

                    CreateGame result = createGameRepository.save(createGame);
                    log.info("추가한 createGameNo"+result.getCreateGameNo());
                }
        );
    } //testInsert

} //Class
