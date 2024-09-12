package com.project.animalface_web.controller;

import com.project.animalface_web.domain.quiz.Quiz;
import com.project.animalface_web.domain.quiz.QuizQuestion;
import com.project.animalface_web.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String startQuiz(@PathVariable Long quiz_no, @PathVariable Long quiz_question_no, Model model) {
        // 유효한 문제 번호 찾기
        Optional<QuizQuestion> questionOpt = quizService.getNextValidQuestion(quiz_no, quiz_question_no);

        if (questionOpt.isPresent()) {
            QuizQuestion currentQuestion = questionOpt.get();
            model.addAttribute("question", currentQuestion);
            model.addAttribute("quizNo", quiz_no);
            model.addAttribute("quizQuestionNo", currentQuestion.getQuizQuestionNo());
            return "quizQuestion"; // 문제 풀기 화면으로 이동
        } else {
            // 유효한 문제가 더 없을 경우 결과 페이지로 이동
            return "redirect:/quiz/" + quiz_no + "/result";
        }
    }

    // 정답 제출 및 다음 문제로 이동
    @PostMapping("/{quiz_no}/answer")
    public String checkAnswer(@RequestParam Long questionId, @RequestParam String userAnswer,
                              @RequestParam Long quizQuestionNo,
                              @ModelAttribute("correctCount") Integer correctCount, Model model) {
        boolean isCorrect = quizService.checkAnswer(questionId, userAnswer);
        QuizQuestion question = quizService.getQuestionById(questionId);

        if (isCorrect) {
            correctCount++;  // 맞춘 문제 수 증가
        }

        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("correctAnswer", question.getAnswer().getCorrectAnswer());
        model.addAttribute("correctCount", correctCount);

        // 다음 문제로 이동 (quizQuestionNo + 1부터 유효한 문제 찾기)
        return "redirect:/quiz/" + question.getQuiz().getQuizNo() + "/start/" + (quizQuestionNo + 1);
    }

    // 결과 페이지 (모든 문제를 다 풀었을 때)
    @GetMapping("/{quiz_no}/result")
    public String showResult(@PathVariable Long quiz_no, @ModelAttribute("correctCount") Integer correctCount, Model model) {
        model.addAttribute("totalCorrect", correctCount);  // 맞춘 문제 개수
        return "quizResult"; // 결과 화면으로 이동
    }
}
