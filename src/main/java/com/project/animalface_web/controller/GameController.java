package com.project.animalface_web.controller;

import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameAnswer;
import com.project.animalface_web.domain.GameQuestion;
import com.project.animalface_web.service.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

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
        log.debug("Fetching game with ID: {}", gameNo);

        Game game = gameService.getGameById(gameNo);
        if (game == null) {
            log.error("Game not found for ID: {}", gameNo);
            return "error"; // 에러 페이지로 리다이렉트
        }

        List<GameQuestion> questions = gameService.getQuestionsByGameId(gameNo);
        log.debug("Found {} questions for game ID: {}", questions.size(), gameNo);

        int pageSize = 2;
        int start = page * pageSize;
        int end = Math.min(start + pageSize, questions.size());

        List<GameQuestion> currentQuestions = questions.subList(start, end);
        model.addAttribute("game", game);
        model.addAttribute("questions", currentQuestions);
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", end < questions.size());

        return "game/view";
    }

    // 질문 보기
    @GetMapping("/{gameNo}/questions/{questionNo}")
    public String viewQuestion(@PathVariable Long gameNo, @PathVariable Long questionNo, Model model) {
        Game game = gameService.getGameById(gameNo);
        log.debug("Fetching question with ID: {}", questionNo);

        GameQuestion question = gameService.getQuestionsByGameId(gameNo).stream()
                .filter(q -> q.getQuestionNo().equals(questionNo))
                .findFirst()
                .orElse(null);

        if (question == null) {
            log.error("Question not found for ID: {}", questionNo);
            throw new IllegalArgumentException("Question not found");
        }

        log.debug("Found question: {}", question);

        List<GameAnswer> answers = gameService.getAnswersByQuestionId(questionNo);
        model.addAttribute("game", game);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        return "game/view";  // templates/game/view.html
    }




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
