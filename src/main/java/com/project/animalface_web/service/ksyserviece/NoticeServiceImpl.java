package com.project.animalface_web.service.ksyserviece;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.ksydto.NoticeDTO;
import com.project.animalface_web.repository.ksyrepository.NoticeRepository;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor

public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;


    @Override
    public Long register(NoticeDTO noticeDTO) {
        Notice notice = dtoToEntity(noticeDTO);
        Long noticeNo = noticeRepository.save(notice).getNoticeNo();
        return noticeNo;
    }

    @Override
    public NoticeDTO read(Long noticeNo) {
        Optional<Notice> result = noticeRepository.findById(noticeNo);
        Notice notice = result.orElse();
    }

    @Override
    public void update(NoticeDTO noticeDTO) {

    }

    @Override
    public void delete(Long noticeNo) {

    }

    @Override
    public void deleteAll(Long noticeNo) {

    }
}
