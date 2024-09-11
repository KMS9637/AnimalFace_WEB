package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.dto.APIUserDTO;
import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/profile/edit")
    public String showEditProfilePage(@AuthenticationPrincipal APIUserDTO user, Model model) {
        Long memberNo = user.getMemberNo();
        Optional<MemberDTO> memberDTOOptional = memberService.getMemberDTOById(memberNo);
        if (memberDTOOptional.isPresent()) {
            model.addAttribute("user", memberDTOOptional.get());
        } else {
            log.error("User not found with memberNo: " + memberNo);
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        return "member/profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute MemberDTO updatedMember, @AuthenticationPrincipal APIUserDTO user) {
        Long memberNo = user.getMemberNo();

        // 사용자 정보를 업데이트
        MemberDTO currentMember = memberService.getMemberDTOById(memberNo)
                .orElseThrow(() -> new RuntimeException("User not found"));

        currentMember.setMemberName(updatedMember.getMemberName());

        memberService.updateMember(memberNo, currentMember);

        return "redirect:/member/profile";
    }

    @GetMapping("/profile")
    public String showProfilePage(@AuthenticationPrincipal APIUserDTO user, Model model) {
        log.info("Authenticated user: " + user);
        if (user == null) {
            log.info("프로필 이동 실패");
            return "redirect:/main"; // 로그인 페이지로 리다이렉트
        }

        Long memberNo = user.getMemberNo();
        Optional<MemberDTO> memberDTOOptional = memberService.getMemberDTOById(memberNo);
        log.info("Authenticated user: " + memberDTOOptional);
        if (memberDTOOptional.isPresent()) {
            model.addAttribute("user", memberDTOOptional.get());
        } else {
            log.error("User not found with memberNo: " + memberNo);
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        return "member/profile";
    }

    @PostMapping("/delete")
    public String deleteUserAccount(@AuthenticationPrincipal APIUserDTO user) {
        log.info("delete1 Authenticated user: " + user);
        if (user == null) {
            return "redirect:/main";
        }

        String memberId = user.getMemberId(); // 로그인한 사용자의 ID를 가져옴
        log.info("delete2 Authenticated user: " + memberId);
        memberService.deleteUser(memberId); // 회원탈퇴 서비스 호출
        return "redirect:/member/logout"; // 탈퇴 후 로그아웃
    }
}
