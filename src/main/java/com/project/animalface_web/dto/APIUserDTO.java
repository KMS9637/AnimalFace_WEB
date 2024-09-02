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
    private String profileImageId;

    public APIUserDTO(String memberId, String memberPw, String memberName, String profileImageId, Collection<GrantedAuthority> authorities) {
        super(memberId, memberPw, authorities);
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.profileImageId = profileImageId;
    }

//    public APIUserDTO(String memberName, String memberPw, String memberId, String profileImageId, Collection<? extends GrantedAuthority> authorities) {
//        this.memberName = memberName;
//        this.memberPw = memberPw;
//        this.memberId = memberId;
//        this.profileImageId = profileImageId;
//        this.authorities = authorities;
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return memberPw;
//    }
//
//    @Override
//    public String getUsername() {
//        return memberId;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // Implement as needed
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // Implement as needed
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // Implement as needed
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // Implement as needed
//    }
}