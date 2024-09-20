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

    @GetMapping
    public String listGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "game/list";  // games/list.html
    }

    @GetMapping("/{gameNo}")
    public String viewGame(@PathVariable Long gameNo, Model model) {
        Game game = gameService.getGameById(gameNo);
        List<GameQuestion> questions = gameService.getQuestionsByGameId(gameNo);
        model.addAttribute("game", game);
        model.addAttribute("questions", questions);
        return "game/view";  // games/view.html
    }

    @GetMapping("/{gameNo}/questions/{questionNo}")
    public String viewQuestion(@PathVariable Long gameNo, @PathVariable Long questionNo, Model model) {
        List<GameAnswer> answers = gameService.getAnswersByQuestionId(questionNo);
        model.addAttribute("answers", answers);
        return "questions/view";  // questions/view.html
    }

    @GetMapping("/create")
    public String createGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "game/create";  // games/create.html
    }

    @PostMapping
    public String createGame(@ModelAttribute Game game) {
        gameService.saveGame(game);
        return "redirect:/game";
    }

    @DeleteMapping("/{gameNo}")
    public String deleteGame(@PathVariable Long gameNo) {
        gameService.deleteGame(gameNo);
        return "redirect:/game";
    }
}

