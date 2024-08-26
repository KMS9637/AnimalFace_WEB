package com.project.animalface_web.controller.kcycontroller;


import com.project.animalface_web.service.kcyserviece.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void allresult() {

    }

}