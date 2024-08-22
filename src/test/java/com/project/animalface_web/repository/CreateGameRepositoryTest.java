package com.project.animalface_web.repository;

import com.project.animalface_web.domain.CreateGame;
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
