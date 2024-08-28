package com.project.animalface_web.service.kdkserviece;

import com.project.animalface_web.domain.CreateGame;
import com.project.animalface_web.domain.FaceType;
import com.project.animalface_web.dto.CreateGameDTO;
import com.project.animalface_web.dto.FaceTypeDTO;

public interface FaceTypeService {

    Long registerFaceType(FaceTypeDTO faceTypeDTO);


    default FaceTypeDTO entityToDTO(FaceType faceType) {
        FaceTypeDTO faceTypeDTO =  FaceTypeDTO.builder()
                .animalType(faceType.getAnimalType())
                .animalImage(faceType.getAnimalImage())
                .animalAccuracy(faceType.getAnimalAccuracy())
                .build();
        return faceTypeDTO;
    }//entityToDTO

    default FaceType dtoToEntity(FaceTypeDTO faceTypeDTO) {
        FaceType faceType = FaceType.builder()
                .animalType(faceTypeDTO.getAnimalType())
                .animalImage(faceTypeDTO.getAnimalImage())
                .animalAccuracy(faceTypeDTO.getAnimalAccuracy())
                .build();
        return faceType;
    }//dtoToEntity








}//Class