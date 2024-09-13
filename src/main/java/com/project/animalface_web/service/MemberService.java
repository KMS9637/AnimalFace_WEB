package com.project.animalface_web.service;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.MemberRole;
import com.project.animalface_web.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getUserById(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    public Optional<Member> getUserById(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public Member createUser(Member user) {

        String encodedPassword = passwordEncoder.encode(user.getMemberPw());
        user.setMemberPw(encodedPassword);
        user.addRole(MemberRole.USER);
        return memberRepository.save(user);
    }

    public Member updateUser(Long id, Member userDetails) {
        Member user = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMemberName(userDetails.getMemberName());
        user.setMemberId(userDetails.getMemberId());

        return memberRepository.save(user);
    }

    public void deleteUser(String memberId) {
        log.info("lsy 2 MemberService memberId : " + memberId);

            memberRepository.deleteByMemberId(memberId);

    }



}

