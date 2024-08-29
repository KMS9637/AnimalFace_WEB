package com.project.animalface_web.domain;

import com.project.animalface_web.dto.ResultDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResult extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "gameNo")
    private Game game;

    private String gameResult;

    private String gameResultImage;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member member;


}

