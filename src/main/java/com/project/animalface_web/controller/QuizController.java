package com.project.animalface_web.controller;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // 퀴즈 목록 페이지
    @GetMapping("/list")
    public String listQuizzes(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "quizList"; // quizList.html 템플릿으로 이동
    }

    // 퀴즈 진행 페이지
    @GetMapping("/{quizId}/start")
    public String startQuiz(@PathVariable Long quizId, Model model) {
        List<QuizQuestion> questions = quizService.getQuizQuestions(quizId);
        model.addAttribute("questions", questions);
        model.addAttribute("quizId", quizId);
        return "quizStart"; // quizStart.html 템플릿으로 이동
    }

    // 정답 제출 및 확인
    @PostMapping("/{quizId}/answer")
    public String checkAnswer(@RequestParam Long questionId, @RequestParam String userAnswer, Model model) {
        boolean isCorrect = quizService.checkAnswer(questionId, userAnswer);
        model.addAttribute("isCorrect", isCorrect);
        return "answerResult"; // answerResult.html 템플릿으로 이동
    }

    // 결과 페이지
    @GetMapping("/{quizId}/result")
    public String showResult(@PathVariable Long quizId, Model model) {
        // 맞춘 정답 개수 계산 로직 추가
        model.addAttribute("result", "정답 개수는 XX개 입니다.");
        return "quizResult"; // quizResult.html 템플릿으로 이동
    }
}
