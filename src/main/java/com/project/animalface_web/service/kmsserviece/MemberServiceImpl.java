package com.project.animalface_web.service.kmsserviece;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.MemberRole;
import com.project.animalface_web.dto.MemberDTO;
import com.project.animalface_web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public MemberDTO registerMember(MemberDTO memberDTO) {
        // DTO를 엔티티로 변환
        Member member = Member.builder()
                .memberId(memberDTO.getMemberId())
                .memberPw(passwordEncoder.encode(memberDTO.getMemberPw())) // 비밀번호 암호화
                .memberName(memberDTO.getMemberName())
                .memberImg(memberDTO.getMemberImg())
                .memberGameResult(memberDTO.getMemberGameResult())
                .uuid(memberDTO.getUuid())
                .fileName(memberDTO.getFileName())
                //.profileImageServer(memberDTO.getProfileImageServer()) // 추가된 프로필 이미지 서버 정보
                .roleSet(new HashSet<>()) // 기본 역할 집합
                .build();

        // 회원 정보 저장
        Member savedMember = memberRepository.save(member);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return MemberDTO.builder()
                .memberNo(savedMember.getMemberNo())
                .memberId(savedMember.getMemberId())
                .memberName(savedMember.getMemberName())
                .memberImg(savedMember.getMemberImg())
                .memberGameResult(savedMember.getMemberGameResult())
                .uuid(savedMember.getUuid())
                .fileName(savedMember.getFileName())
                //.profileImageServer(savedMember.getProfileImageServer())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                member.getMemberId(),
                member.getMemberPw(),
                new ArrayList<>()
        );
    }

    // 로그인 메서드 추가
    public boolean loginMember(MemberDTO memberDTO) {
        // 사용자 ID로 회원 정보 조회
        Member member = memberRepository.findByMemberId(memberDTO.getMemberId());

        if (member == null) {
            return false; // 아이디가 존재하지 않음
        }

        return passwordEncoder.matches(memberDTO.getMemberPw(), member.getMemberPw());
    }
}
