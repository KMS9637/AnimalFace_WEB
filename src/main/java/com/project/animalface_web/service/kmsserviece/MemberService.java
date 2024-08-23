package com.project.animalface_web.service.kmsserviece;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.dto.MemberDTO;

public interface MemberService {
        Member saveEntity(Member member);
        Member saveDto(MemberDTO memberDTO);
}