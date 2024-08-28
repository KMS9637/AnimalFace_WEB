package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberGameResult;

    private String memberImg;

    private boolean del;
    private boolean social;

    //이미지 파일명 필요해서,
    // 프로필 이미지 조회시 사용.
    private String uuid;
    private String fileName;

    // 소셜 로그인한 프로필 이미지, 미디어 서버 주소
    private String profileImageServer;

    // 멤버를 조회시 roleSet 를 같이 조회를 하기.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void changePassword(String memberPw) {
        this.memberPw = memberPw;
    }
    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);

    }
}

