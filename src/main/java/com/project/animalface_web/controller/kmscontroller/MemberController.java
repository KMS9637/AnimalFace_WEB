package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.mongoEntity.ProfileImage;
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
//        model.addAttribute("user", new User());
        model.addAttribute("user", user);
    }

    //프로필 이미지 업로드 형식으로, 몽고디비에 연결하는 코드
    @PostMapping("/register")
    public String createUser(@ModelAttribute Member user, @RequestParam("profileImage") MultipartFile file) {
        log.info("lsy User created" + user, "multipart : " + file
        );
//        try {
            // 비밀번호 암호화
            user.setMemberPw(bCryptPasswordEncoder.encode(user.getMemberPw()));
            // 사용자 저장
            Member savedUser = memberService.createUser(user);

            // 프로필 이미지 저장
//            if (file != null && !file.isEmpty()) {
//                memberService.saveProfileImage(savedUser.getMemberNo(), file);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to save user profile image", e);
//        }
        return "redirect:/member/login";
        // Redirect to the list of users
    }

    @GetMapping("/{id}/edit")
    public void showUpdateUserForm(@PathVariable Long id, Model model) {
        memberService.getUserById(id).ifPresent(user -> model.addAttribute("user", user));
    }

//    @PostMapping("/edit")
//    public String updateUser( @ModelAttribute Member user , @RequestParam("profileImage") MultipartFile file) {
//
//        try {
//            if (!file.isEmpty()) {
//                // 기존 프로필 삭제
//                Optional<Member> loadUser = memberService.getUserById(user.getMemberNo());
//                Member loadedUser = loadUser.get();
//                memberService.deleteProfileImage(loadedUser);
//                // 프로필 이미지 업데이트
//                memberService.saveProfileImage(user.getMemberNo(), file);
//                memberService.updateUser( user.getMemberNo(), user);
//            } else {
//                memberService.updateUser( user.getMemberNo(), user);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to save user profile image", e);
//        }
//
//        return "redirect:/users";
//        // Redirect to the list of users
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteUser(@PathVariable Long id) {
//        memberService.deleteUser(id);
//        return "redirect:/users";
//        // Redirect to the list of users
//    }

//    @GetMapping("/{id}/profileImage")
//    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long id) {
//        log.info("lsy users image 확인 ");
//        Optional<Member> user = memberService.getUserById(id);
//        if (user.isPresent() && user.get().getProfileImageId() != null) {
//            ProfileImage profileImage = memberService.getProfileImage(user.get().getProfileImageId());
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(profileImage.getContentType()))
//                    .body(profileImage.getData());
//        }
//        return ResponseEntity.notFound().build();
//    }
}
