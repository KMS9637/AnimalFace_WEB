package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameNo;

    private String gameName;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameQuestion> questions;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameAnswer> answers;


}


