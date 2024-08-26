package com.project.animalface_web.service.kmsserviece;

import com.project.animalface_web.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {
        MemberDTO registerMember(MemberDTO memberDTO);
        UserDetails loadUserByUsername(String username);
        boolean loginMember(MemberDTO memberDTO);
}
