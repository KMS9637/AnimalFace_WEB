package com.project.animalface_web.service.ksyserviece;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.ksydto.NoticeDTO;
import com.project.animalface_web.repository.ksyrepository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor

public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<NoticeDTO> getNotices() {
        List<Notice> notices = noticeRepository.findAll();
        // Convert Notice entities to NoticeDTOs
        return notices.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDTO read(Long noticeNo) {
        Optional<Notice> result = noticeRepository.findById(noticeNo);
        Notice notice = result.orElseThrow();
        NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);
        return noticeDTO;
    }
}
