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

        // 2. 첫 번째 질문과 정답 생성
        QuizQuestion question1 = QuizQuestion.builder()
                .questionText("한국의 수도는 어디인가?")
                .quiz(quiz)  // 이 질문이 속하는 퀴즈 설정
                .build();

        QuizAnswer answer1 = QuizAnswer.builder()
                .correctAnswer("서울")
                .question(question1)  // 이 정답이 속하는 질문 설정
                .build();

        question1.setAnswer(answer1); // 질문에 정답 연결

        // 3. 두 번째 질문과 정답 생성
        QuizQuestion question2 = QuizQuestion.builder()
                .questionText("대한민국의 공식 언어는 무엇인가?")
                .quiz(quiz)  // 이 질문이 속하는 퀴즈 설정
                .build();

        QuizAnswer answer2 = QuizAnswer.builder()
                .correctAnswer("한국어")
                .question(question2)  // 이 정답이 속하는 질문 설정
                .build();

        question2.setAnswer(answer2); // 질문에 정답 연결

        // 4. 퀴즈에 질문 목록 설정
        quiz.setQuestions(List.of(question1, question2));

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
