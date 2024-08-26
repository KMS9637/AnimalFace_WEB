package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.kmsserviece.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 로그인 엔드포인트 추가
    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody MemberDTO memberDTO) {
        boolean isAuthenticated = memberService.loginMember(memberDTO);

        if (isAuthenticated) {
            return ResponseEntity.ok("로그인 성공");  // 로그인 성공 시
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");  // 로그인 실패 시
        }
    }
}
