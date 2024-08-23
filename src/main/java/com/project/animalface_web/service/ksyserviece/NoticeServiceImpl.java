package com.project.animalface_web.service.ksyserviece;

import com.project.animalface_web.dto.ksydto.NoticeDTO;
import com.project.animalface_web.repository.ksyrepository.NoticeRepository;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
        Notice notice = result.orElseThrow();
        NoticeDTO noticeDTO = entityToDto(notice);
        return noticeDTO;
    }

    @Override
    public void update(NoticeDTO noticeDTO) {
        Optional<Notice> result = noticeRepository.findById(noticeDTO.getNoticeNo());
        Notice notice = result.orElseThrow();
        notice.updateNameAndContent(noticeDTO.getNoticeName(), noticeDTO.getNoticeContents());
        noticeRepository.save(notice);
    }

    @Override
    public void delete(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }

}
