package com.project.animalface_web.service.kcyserviece;

import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.dto.ResultDTO;
import java.util.List;

public interface ResultService {
    List<ResultDTO> getAllresults();  // 메서드 이름 수정
    ResultDTO entityToDTO(GameResult result);  // XML의 Result 클래스 대신에 엔티티 클래스 사용
}