package com.project.animalface_web.service.kdkserviece;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.dto.CreateGameDTO;

public interface CreateGameService {

    Long registerCreateGame(CreateGameDTO createGameDTO);

    default CreateGameDTO entityToDTO(CreateGame createGame) {
        CreateGameDTO createGameDTO = CreateGameDTO.builder()
                .createGameNo(createGame.getCreateGameNo())
                .createAnswer(createGame.getCreateGameAnswer())
                .createGameName(createGame.getCreateGameName())
                .createQuestion(createGame.getCreateGameQuestion())
                .createResult(createGame.getCreateGameResult())
                .build();
        return createGameDTO;
    }//entityToDTO

    default CreateGame dtoToEntity(CreateGameDTO createGameDTO) {
        CreateGame createGame = CreateGame.builder()
                .createGameNo(createGameDTO.getCreateGameNo())
                .createGameQuestion(createGameDTO.getCreateQuestion())
                .createGameName(createGameDTO.getCreateGameName())
                .createGameAnswer(createGameDTO.getCreateAnswer())
                .createGameResult(createGameDTO.getCreateResult())
                .build();
        return createGame;
    }//dtoToEntity


}//class

