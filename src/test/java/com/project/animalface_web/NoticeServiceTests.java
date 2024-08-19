package com.project.animalface_web;

import com.project.animalface_web.domain.Notice;
import com.project.animalface_web.repository.ksyrepository.NoticeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class NoticeServiceTests {
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
                            .date(new Date())
                            .build();


                    Notice result = noticeRepository.save(notice);
                    log.info("추가한 BNO : " + result.getNoticeNo());
                }
        );
    }
}
