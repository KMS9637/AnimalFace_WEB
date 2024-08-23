package com.project.animalface_web.repository;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.CreateGameQuestion;
import com.project.animalface_web.domain.CreateGameAnswer;
import com.project.animalface_web.domain.CreateGameResult;
import com.project.animalface_web.repository.kdkrepository.createGameRepository.CreateGameAnswerRepository;
import com.project.animalface_web.repository.kdkrepository.createGameRepository.CreateGameQuestionRepository;
import com.project.animalface_web.repository.kdkrepository.createGameRepository.CreateGameRepository;
import com.project.animalface_web.repository.kdkrepository.createGameRepository.CreateGameResultRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CreateGameRepositoryTest {

    @Autowired
    CreateGameRepository createGameRepository;

    @Autowired
    CreateGameQuestionRepository createGameQuestionRepository;

    @Autowired
    CreateGameAnswerRepository createGameAnswerRepository;

    @Autowired
    CreateGameResultRepository createGameResultRepository;

    @Test
    public void createGameWithQuestionsAnswersAndResults() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            CreateGame createGame = CreateGame.builder()
                    .createGameName("생성 퀴즈 제목 더미데이터 " + i)
                    .build();

            // CreateGameQuestion 리스트 생성
            List<CreateGameQuestion> createGameQuestionList = new ArrayList<>();
            List<CreateGameAnswer> createGameAnswerList = new ArrayList<>();
            List<CreateGameResult> createGameResultList = new ArrayList<>();

            IntStream.rangeClosed(1, 5).forEach(j -> {
                CreateGameQuestion createGameQuestion = CreateGameQuestion.builder()
                        .createQuestion("생성 퀴즈 질문 더미 " + j + " (게임 " + i + ")")
                        .createGame(createGame) // 관계 설정
                        .build();

                createGameQuestionList.add(createGameQuestion);

                CreateGameAnswer createGameAnswer = CreateGameAnswer.builder()
                        .createAnswer("생성 퀴즈 답변. 더미 " + j + " (게임 " + i + ")")
                        .createGame(createGame) // 관계 설정
                        .build();

                createGameAnswerList.add(createGameAnswer);

                CreateGameResult createGameResult = CreateGameResult.builder()
                        .createResult("생성 퀴즈 결과 더미 " + j + " (게임 " + i + ")")
                        .createGame(createGame) // 관계 설정
                        .build();

                createGameResultList.add(createGameResult);
            });

            // CreateGame에 질문, 답변, 결과 리스트 설정
            createGame.setCreateGameQuestions(createGameQuestionList);
            createGame.setCreateGameAnswers(createGameAnswerList);
            createGame.setCreateGameResults(createGameResultList);

            // CreateGame 저장 (Cascade 옵션으로 인해 관련 엔티티들도 함께 저장됨)
            CreateGame result = createGameRepository.save(createGame);
            log.info("추가한 createGameNo: " + result.getCreateGameNo());
        });
    }

} //Class
