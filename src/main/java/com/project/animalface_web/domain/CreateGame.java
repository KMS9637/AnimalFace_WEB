package com.project.animalface_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long createGameNo;

    private String createGameName;
    private String createQuestion;
    private String createAnswer;
    private String createResult;
}

