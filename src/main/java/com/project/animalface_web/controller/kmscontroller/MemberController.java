package com.project.animalface_web.controller.kmscontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/members")
public class MemberController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";  // `src/main/resources/templates/login.html`
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";  // `src/main/resources/templates/register.html`
    }


}
