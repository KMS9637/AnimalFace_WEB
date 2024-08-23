package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.kmsserviece.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 로그인 페이지 GET 요청
    @GetMapping("/login")
    public ResponseEntity<String> getLoginPage() {
        // 로그인 페이지의 HTML을 반환합니다.
        // 또는 템플릿 엔진을 사용하는 경우 템플릿 이름을 반환합니다.
        return ResponseEntity.ok("<html><body>로그인 페이지입니다.</body></html>");
    }

    // 회원가입 페이지 GET 요청
    @GetMapping("/register")
    public ResponseEntity<String> getRegisterPage() {
        // 회원가입 페이지의 HTML을 반환합니다.
        // 또는 템플릿 엔진을 사용하는 경우 템플릿 이름을 반환합니다.
        return ResponseEntity.ok("<html><body>회원가입 페이지입니다.</body></html>");
    }

//    // 회원가입 API
//    @PostMapping("/register")
//    public ResponseEntity<MemberResponseDto> registerMember(@RequestBody MemberRequestDto memberRequestDto) {
//        MemberResponseDto responseDto = memberService.registerMember(memberRequestDto);
//        return ResponseEntity.ok(responseDto);
//    }
//
//    // 로그인 API (기본적인 예제, JWT 토큰 반환을 위한 코드 필요)
//    @PostMapping("/login")
//    public ResponseEntity<?> loginMember(@RequestBody MemberRequestDto memberRequestDto) {
//        // 로그인 로직을 처리하고 JWT 토큰을 반환합니다
//        // JWT 토큰 반환 로직 구현 필요
//        return ResponseEntity.ok().body("로그인 성공");
//    }
}