package com.project.animalface_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResultDTO {
    private Long UserNo;
    private Long gameNo;
    private String gameResult;
}
