package com.project.animalface_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionDTO {
    private Long quizQuestionNo;  // 질문 번호
    private String questionText;  // 질문 텍스트
    private QuizAnswerDTO answer;  // 질문에 대한 정답 정보
}