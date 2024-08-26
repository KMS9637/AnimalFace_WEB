package com.project.animalface_web.controller.ohjcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test {

    @GetMapping("/home")
    public String home() {
        return "index"; // templates 폴더에 있는 index.html 파일을 찾습니다.
    }
}