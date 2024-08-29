package com.project.animalface_web.service.kcyserviece;

import com.project.animalface_web.domain.GameResult;
import com.project.animalface_web.dto.ResultDTO;
import com.project.animalface_web.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private GameResultRepository gameResultRepository; // 인스턴스 변수 사용

    @Override
    public List<ResultDTO> getAllresults() {
        // 인스턴스 변수를 사용하여 findAll() 메서드 호출
        return gameResultRepository.findAll().stream() // 인스턴스 변수 사용으로 수정
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResultDTO entityToDTO(GameResult result) {
        ResultDTO resultDTO = ResultDTO.builder()
                .gameNo(result.getGame().getGameNo())
                .gameResult(result.getGameResult())
                .gameResult(result.getGameResultImage()) // 잘못된 메서드 수정
                .build();

        return resultDTO;
    }
}
