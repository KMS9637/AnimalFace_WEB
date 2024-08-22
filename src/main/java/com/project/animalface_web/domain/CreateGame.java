package com.project.animalface_web.domain;

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
public class CreateGame extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long createGameNo;

    private String createGameName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CreateGameQuestion> createGameQuestions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CreateGameAnswer> createGameAnswers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CreateGameResult> createGameResults;
}

