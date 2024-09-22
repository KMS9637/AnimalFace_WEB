package com.project.animalface_web.controller;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.service.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private long currentQuestionIndex = 0;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // 게임 목록 조회
    @GetMapping
    public String listGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "game/list";  // games/list.html
    }

    // 특정 게임의 질문 조회
    @GetMapping("/{gameNo}")
    public String showQuestions(@PathVariable Long gameNo, Model model, @RequestParam(defaultValue = "0") int page) {
        List<GameQuestion> questions = gameService.getQuestionsByGameId(gameNo); // gameNo로 변경

        if (currentQuestionIndex < questions.size()) {
            GameQuestion question = questions.get((int) currentQuestionIndex);
            model.addAttribute("question", question);
            model.addAttribute("answers", question.getAnswers());
            model.addAttribute("gameNo", gameNo);
            return "game/view";
        } else {
            return "game/result"; // 질문 소진 시 결과 페이지
        }


    }

    @PostMapping("/{gameNo}/answer")
    public String answerQuestion(@PathVariable Long gameNo, @RequestParam String answer) {
        currentQuestionIndex++;
        return "redirect:/game/" + gameNo;
    }



    // 질문 보기




    // 게임 생성 폼
    @GetMapping("/create")
    public String createGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "game/create";  // games/create.html
    }

    // 게임 생성 처리
    @PostMapping
    public String createGame(@ModelAttribute Game game) {
        gameService.saveGame(game);
        return "redirect:/game";
    }

    // 게임 삭제 처리
    @DeleteMapping("/{gameNo}")
    public String deleteGame(@PathVariable Long gameNo) {
        gameService.deleteGame(gameNo);
        return "redirect:/game";
    }
}
