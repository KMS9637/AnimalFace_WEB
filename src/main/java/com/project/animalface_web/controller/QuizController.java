package com.project.animalface_web.controller;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.dto.QuizDTO;
import com.project.animalface_web.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quiz")
@SessionAttributes("correctCount")  // 세션에서 맞춘 정답 개수를 유지
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @ModelAttribute("correctCount")
    public Integer correctCount() {
        return 0;  // 맞춘 정답의 초기 값
    }

    // 퀴즈 시작 및 특정 문제 가져오기
    @GetMapping("/{quiz_no}/start/{quiz_question_no}")
    public String startQuiz(@PathVariable Long quiz_no, @PathVariable Long quiz_question_no,
                            Model model, @SessionAttribute("correctCount") Integer correctCount) {

        Long quizQuestionNo = quiz_question_no;

        // 퀴즈 시작 시 정답 갯수를 초기화
        if (quizQuestionNo == 0L) {
            correctCount = 0;  // 새로운 퀴즈 시작 시 정답 갯수 리셋
        }

        // 유효한 문제 번호 찾기
        Optional<QuizQuestion> questionOpt = quizService.getNextValidQuestion(quiz_no, quiz_question_no);

        if (questionOpt.isPresent()) {
            QuizQuestion currentQuestion = questionOpt.get();
            model.addAttribute("question", currentQuestion);
            model.addAttribute("quizNo", quiz_no);
            model.addAttribute("quizQuestionNo", currentQuestion.getQuizQuestionNo());
            model.addAttribute("correctCount", correctCount);  // 정답 갯수 모델에 추가
            return "quizQuestion"; // 문제 풀기 화면으로 이동
        } else {
            return "redirect:/quiz/" + quiz_no + "/result";
        }
    }


    @PostMapping("/{quiz_no}/answer")
    public String checkAnswer(@RequestParam Long questionId, @RequestParam String userAnswer,
                              @RequestParam Long quizQuestionNo,
                              @ModelAttribute("correctCount") Integer correctCount, Model model) {
        boolean isCorrect = quizService.checkAnswer(questionId, userAnswer);
        QuizQuestion question = quizService.getQuestionById(questionId);

        if (isCorrect) {
            correctCount++;  // 맞춘 문제 수 증가
        }

        // 다음 문제 번호 계산
        Long nextQuestionNo = quizQuestionNo;

        // 로그를 추가해 값을 확인
        System.out.println("Current Question No: " + quizQuestionNo);
        System.out.println("Next Question No: " + nextQuestionNo);

        // 모델에 필요한 값 추가
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("correctAnswer", question.getAnswer().getCorrectAnswer());
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("quizNo", question.getQuiz().getQuizNo());
        model.addAttribute("quizQuestionNo", quizQuestionNo);
        model.addAttribute("nextQuestionNo", nextQuestionNo);  // 다음 문제 번호 추가

        return "quiz/answerResult";  // 결과 페이지로 이동
    }



    // 결과 페이지 (모든 문제를 다 풀었을 때)
    @GetMapping("/{quiz_no}/result")
    public String showResult(@PathVariable Long quiz_no,
                             @ModelAttribute("correctCount") Integer correctCount,
                             SessionStatus sessionStatus, Model model) {

        // 맞춘 문제 수를 모델에 추가
        model.addAttribute("totalCorrect", correctCount);

        // 세션 종료 (세션에 저장된 correctCount를 리셋)
        sessionStatus.setComplete();

        return "quizResult"; // 결과 화면으로 이동
    }


    // JSON 형식으로 퀴즈 목록 반환
    @GetMapping("/api/list")
    @ResponseBody  // JSON 형식으로 반환
    public List<QuizDTO> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // HTML 페이지 렌더링 (퀴즈 목록 보기)
    @GetMapping("/list")
    public String getQuizList(Model model) {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "quiz/list"; // Thymeleaf 템플릿 "list.html" 렌더링
    }
}
