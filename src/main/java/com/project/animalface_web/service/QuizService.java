package com.project.animalface_web.service;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizAnswer;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.dto.QuizAnswerDTO;
import com.project.animalface_web.dto.QuizDTO;
import com.project.animalface_web.dto.QuizQuestionDTO;
import com.project.animalface_web.repository.QuizAnswerRepository;
import com.project.animalface_web.repository.QuizQuestionRepository;
import com.project.animalface_web.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return questionRepository.findFirstByQuiz_QuizNoAndQuizQuestionNoGreaterThanOrderByQuizQuestionNoAsc(quiz_no, quiz_question_no);
    }

    // 특정 질문 가져오기 (getQuestionById)
    public QuizQuestion getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문을 찾을 수 없습니다. ID: " + questionId));
    }

    // 정답 확인 (quiz_question_no와 매칭된 정답 확인)
    public boolean checkAnswer(Long questionId, String userAnswer) {
        QuizAnswer answer = answerRepository.findByQuestion_QuizQuestionNo(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 정답을 찾을 수 없습니다. ID: " + questionId));
        return answer.getCorrectAnswer().equalsIgnoreCase(userAnswer);
    }

    // 퀴즈 목록을 데이터베이스에서 가져와서 DTO로 변환
    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();  // DB에서 퀴즈 목록 가져오기
        // Quiz -> QuizDTO로 변환
        return quizzes.stream().map(quiz -> {
            List<QuizQuestionDTO> questionDTOs = quiz.getQuestions().stream().map(question -> {
                QuizAnswerDTO answerDTO = null;  // 기본값은 null로 설정

                // 정답이 존재하는 경우에만 QuizAnswerDTO를 생성
                if (question.getAnswer() != null) {
                    answerDTO = QuizAnswerDTO.builder()
                            .quizAnswerNo(question.getAnswer().getQuizAnswerNo())
                            .correctAnswer(question.getAnswer().getCorrectAnswer())
                            .build();
                }

                return QuizQuestionDTO.builder()
                        .quizQuestionNo(question.getQuizQuestionNo())
                        .questionText(question.getQuestionText())
                        .answer(answerDTO)  // 정답이 null일 수도 있음
                        .build();
            }).collect(Collectors.toList());

            return QuizDTO.builder()
                    .quizNo(quiz.getQuizNo())
                    .quizName(quiz.getQuizName())
                    .questions(questionDTOs)
                    .build();
        }).collect(Collectors.toList());
    }
}