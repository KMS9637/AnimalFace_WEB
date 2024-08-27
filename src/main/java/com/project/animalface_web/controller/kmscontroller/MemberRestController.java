package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.kmsserviece.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO registeredMember = memberService.registerMember(memberDTO);
        return ResponseEntity.ok(registeredMember);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginMember(HttpServletRequest request, @RequestBody MemberDTO memberDTO) {
        boolean isAuthenticated = memberService.loginMember(memberDTO);

        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("user", memberDTO); // 로그인한 사용자 정보를 세션에 저장
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);  // JSON 응답
        } else {
            response.put("message", "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // JSON 응답
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "로그아웃 성공");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> getLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("user") != null;

        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", isLoggedIn);
        return ResponseEntity.ok(response);
    }
}
