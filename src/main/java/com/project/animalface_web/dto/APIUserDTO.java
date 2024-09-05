package com.project.animalface_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString
//public class APIUserDTO implements UserDetails {
public class APIUserDTO extends User {
    private String memberName;
    private String memberPw;
    private String memberId;

    public APIUserDTO(String memberId, String memberPw, String memberName, Collection<GrantedAuthority> authorities) {
        super(memberId, memberPw, authorities);
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
    }//APIUserDTO

}//Class