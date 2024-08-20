package com.project.animalface_web.dto.kdkdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateGameDTO {
    @NotEmpty
    private String createGameName;

    @NotEmpty
    private String createQuestion;

    @NotEmpty
    private String createAnswer;

    @NotEmpty
    private String createResult;

}
