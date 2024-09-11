package com.project.animalface_web.service;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizAnswer;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.repository.QuizAnswerRepository;
import com.project.animalface_web.repository.QuizQuestionRepository;
import com.project.animalface_web.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizQuestionRepository questionRepository;
    private final QuizAnswerRepository answerRepository;

    public QuizService(QuizRepository quizRepository, QuizQuestionRepository questionRepository, QuizAnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    // 퀴즈 목록 조회
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // 특정 퀴즈 질문들 조회
    public List<QuizQuestion> getQuizQuestions(Long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        return quiz.map(Quiz::getQuestions).orElse(null);
    }

    // 정답 확인
    public boolean checkAnswer(Long questionId, String userAnswer) {
        Optional<QuizAnswer> answer = answerRepository.findById(questionId);
        return answer.map(a -> a.getCorrectAnswer().equalsIgnoreCase(userAnswer)).orElse(false);
    }
}
