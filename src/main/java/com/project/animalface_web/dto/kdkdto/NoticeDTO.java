package com.project.animalface_web.dto.kdkdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NoticeDTO {
    private Long noticeNo;
    private String noticeName;
    private String noticeContents;
    private Date date;
}
