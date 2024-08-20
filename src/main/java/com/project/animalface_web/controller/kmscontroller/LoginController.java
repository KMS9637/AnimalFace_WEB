package com.project.animalface_web.controller.kmscontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login3"; // resources/templates/list.html 파일을 렌더링
    }
}
