package com.project.animalface_web.dto.kdkdto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NoticeDTO {

    private String noticeName;
    private String noticeContents;
    private LocalDateTime regDate;
}
