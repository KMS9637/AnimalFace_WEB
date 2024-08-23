package com.project.animalface_web.service.kmsserviece;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.repository.MemberRepository;
import com.project.animalface_web.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
        private final MemberRepository memberRepository;

        @Autowired
        public MemberServiceImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public  Member saveEntity(Member member) {
            return memberRepository.save(member);
        }

        @Override
        public Member saveDto(MemberDTO memberDTO) {
            Member member = Member.builder()
                    .memberId(memberDTO.getMemberId())
                    .memberPw(memberDTO.getMemberPw())
                    .memberName(memberDTO.getMemberName())
                    .build();
            return saveEntity(member);
        }
}