package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@Log4j2
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/main")
    public void getAllUsers(@AuthenticationPrincipal UserDetails user, Model model) {
        List<Member> users = memberService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
    }

    @GetMapping("/login")
    public void showLoginUserForm() {

    }

    @GetMapping("/register")
    public void showCreateUserForm(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("user", user);
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute Member user, @RequestParam("profileImage") MultipartFile file) {
        log.info("lsy User created" + user, "multipart : " + file
        );

            user.setMemberPw(bCryptPasswordEncoder.encode(user.getMemberPw()));

            Member savedUser = memberService.createUser(user);

        return "redirect:/member/login";

    }

    @GetMapping("/{id}/edit")
    public void showUpdateUserForm(@PathVariable Long id, Model model) {
        memberService.getUserById(id).ifPresent(user -> model.addAttribute("user", user));
    }


}
