package com.project.animalface_web.controller.kdkcontroller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/createGame")
public class CreateGameController {

    @GetMapping("/create")
    public String create(Model model) {
        return "createGame/create2";
    }//getMapping

}//Class
