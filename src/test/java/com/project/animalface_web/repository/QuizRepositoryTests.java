package com.project.animalface_web.repository;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizAnswer;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class QuizRepositoryTests {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizAnswerRepository quizAnswerRepository;

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    @Test
    public void QuizInsertTest() {
        // 1. 퀴즈 생성
        Quiz quiz = Quiz.builder()
                .quizName("일반 상식 퀴즈")
                .build();

        // 2. 질문과 정답 목록 생성
        List<QuizQuestion> questions = List.of(
                QuizQuestion.builder().questionText("지구에서 가장 큰 바다는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("임진왜란이 일어난 시기는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라 마지막 황제 이름?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("독도는 우리나라 어디지역의 도인가?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("초등학교 전 학교의 명칭은?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("허준의 의학 서적 이름은?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라 특산 개 품종 이름은?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("일본의 화폐 단위는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("하루 24시간은 몇 분일까?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라에서 가장 돈이 많은 사람?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라에서 세번째로 많은 성씨는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("마라톤의 거리는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라 영화 중 가장 많은 관객이 본 영화는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("2002년 한일 월드컵 우리나라 순위는?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("백제 황산벌 전투 장군으로 유명한 사람?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("아이언맨 주인공 이름?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("마블 영화 중 초록색 괴물로 변하는 캐릭터?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("콧물이 날 때 가는 병원?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라에서 가장 높은 산?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("안동의 대표적인 관광지?").quiz(quiz).build(),
                QuizQuestion.builder().questionText("우리나라 전통 가옥(집) 이름?").quiz(quiz).build()
        );

        List<QuizAnswer> answers = List.of(
                QuizAnswer.builder().correctAnswer("태평양").build(),
                QuizAnswer.builder().correctAnswer("1592년").build(),
                QuizAnswer.builder().correctAnswer("순종").build(),
                QuizAnswer.builder().correctAnswer("경상북도").build(),
                QuizAnswer.builder().correctAnswer("국민학교").build(),
                QuizAnswer.builder().correctAnswer("동의보감").build(),
                QuizAnswer.builder().correctAnswer("진돗개").build(),
                QuizAnswer.builder().correctAnswer("엔").build(),
                QuizAnswer.builder().correctAnswer("1440분").build(),
                QuizAnswer.builder().correctAnswer("이재용").build(),
                QuizAnswer.builder().correctAnswer("박씨").build(),
                QuizAnswer.builder().correctAnswer("42,195KM").build(),
                QuizAnswer.builder().correctAnswer("명량").build(),
                QuizAnswer.builder().correctAnswer("4위").build(),
                QuizAnswer.builder().correctAnswer("계백").build(),
                QuizAnswer.builder().correctAnswer("토니 스타크").build(),
                QuizAnswer.builder().correctAnswer("헐크").build(),
                QuizAnswer.builder().correctAnswer("이빈인후과").build(),
                QuizAnswer.builder().correctAnswer("한라산").build(),
                QuizAnswer.builder().correctAnswer("하회마을").build(),
                QuizAnswer.builder().correctAnswer("한옥").build()
        );

        // 3. 각 질문에 정답 연결
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            QuizAnswer answer = answers.get(i);
            question.setAnswer(answer);
            answer.setQuestion(question); // 정답에 질문 설정
        }

        // 4. 퀴즈에 질문 목록 설정
        quiz.setQuestions(questions);

        // 5. 퀴즈 저장 (CascadeType.ALL 때문에 관련된 질문과 정답도 자동으로 저장됨)
        quizRepository.save(quiz);

        // 6. 저장 후 로그 출력
        log.info("Saved Quiz ID: {}", quiz.getQuizNo());
        log.info("Saved Quiz Name: {}", quiz.getQuizName());

        for (QuizQuestion question : quiz.getQuestions()) {
            log.info("Saved Question ID: {}, Text: {}", question.getQuizQuestionNo(), question.getQuestionText());
            log.info("Saved Answer ID: {}, Correct Answer: {}", question.getAnswer().getQuizAnswerNo(), question.getAnswer().getCorrectAnswer());
        }
    }
}