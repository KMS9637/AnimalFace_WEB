package com.project.animalface_web.controller.kcycontroller;


import com.project.animalface_web.domain.Game;
import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.dto.ResultDTO;
import com.project.animalface_web.service.kcyserviece.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
@Log4j2
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/result")
    public void result() {

    }

    @GetMapping("/all_result")
    public String getAllResults(GameResult game) {
        List<ResultDTO> results = resultService.getAllresults();  // 모든 결과를 가져옴
        game.getResultId();  // 모델에 결과 리스트 추가
        return "results";  // 'results.html' 템플릿 반환
    }

    }
