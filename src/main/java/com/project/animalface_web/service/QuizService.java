package com.project.animalface_web.service;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizAnswer;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.repository.QuizAnswerRepository;
import com.project.animalface_web.repository.QuizQuestionRepository;
import com.project.animalface_web.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository questionRepository;
    private final QuizAnswerRepository answerRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizQuestionRepository questionRepository, QuizAnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    // 특정 퀴즈에서 유효한 다음 문제 찾기
    public Optional<QuizQuestion> getNextValidQuestion(Long quiz_no, Long quiz_question_no) {
        return questionRepository.findFirstByQuizIdAndQuizQuestionNoGreaterThanOrderByQuizQuestionNoAsc(quiz_no, quiz_question_no);
    }

    // 특정 질문 가져오기 (getQuestionById)
    public QuizQuestion getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문을 찾을 수 없습니다. ID: " + questionId));
    }

    // 정답 확인 (quiz_question_no와 매칭된 정답 확인)
    public boolean checkAnswer(Long questionId, String userAnswer) {
        QuizAnswer answer = answerRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 정답을 찾을 수 없습니다. ID: " + questionId));
        return answer.getCorrectAnswer().equalsIgnoreCase(userAnswer);
    }
}
