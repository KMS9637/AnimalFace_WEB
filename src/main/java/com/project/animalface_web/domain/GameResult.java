package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "gameNo")
    private Game game;

    private String gameResult;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member member;
}

