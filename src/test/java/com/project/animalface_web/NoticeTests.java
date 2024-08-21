package com.project.animalface_web;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.dto.ksydto.NoticeDTO;
import com.project.animalface_web.repository.ksyrepository.NoticeRepository;
import com.project.animalface_web.service.ksyserviece.NoticeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class NoticeTests {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeRepository noticeRepository;


    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i->
                {
                    Notice notice = Notice.builder()
                            .noticeNo((long) i)
                            .noticeName("공지사항 제목"+i)
                            .noticeContents("공지사항 내용"+i)
                            .date(LocalDate.now())
                            .build();


                    Notice result = noticeRepository.save(notice);
                    log.info("추가한 BNO : " + result.getNoticeNo());
                }
        );
    }

    @Test
    public void testRead() {
        NoticeDTO noticeDTO = noticeService.read(5L);
        log.info("하나 조회 noticeDTO : "+noticeDTO);
    }

    @Test
    public void testUpdate() {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeNo(5L)
                .noticeName("공지사항 제목 수정")
                .noticeContents("공지사항 내용 수정")
                .date(LocalDate.now())
                .build();
        noticeService.update(noticeDTO);
    }

    @Test
    public void testDelete() {
        noticeService.delete(5L);
    }
}
