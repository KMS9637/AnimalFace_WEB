package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String memberId;
    private String memberPw;
    private String memberImg;
    private String memberName;
    private String memberResult;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<GameResult> gameResults;
}

