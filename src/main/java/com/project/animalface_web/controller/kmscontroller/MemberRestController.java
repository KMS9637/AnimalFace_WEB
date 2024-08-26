package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.kmsserviece.MemberService;
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
    public ResponseEntity<Map<String, String>> loginMember(@RequestBody MemberDTO memberDTO) {
        boolean isAuthenticated = memberService.loginMember(memberDTO);

        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);  // JSON 응답
        } else {
            response.put("message", "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // JSON 응답
        }
    }
}
