package com.project.animalface_web.service.ksyserviece;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.ksydto.NoticeDTO;

import java.util.List;


public interface NoticeService {
    List<NoticeDTO> getNotices();
    NoticeDTO read(Long noticeNo);
    void update(NoticeDTO noticeDTO);
    void delete(Long noticeNo);

    default Notice dtoToEntity(NoticeDTO noticeDTO) {
        Notice notice = Notice.builder()
                .noticeNo(noticeDTO.getNoticeNo())
                .noticeName(noticeDTO.getNoticeName())
                .noticeContents(noticeDTO.getNoticeContents())
                .date(noticeDTO.getDate())
                .build();
        return notice;
    }

    default NoticeDTO entityToDto(Notice notice) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeNo(notice.getNoticeNo())
                .noticeName(notice.getNoticeName())
                .noticeContents(notice.getNoticeContents())
                .date(notice.getDate())
                .build();
        return noticeDTO;
    }
}
