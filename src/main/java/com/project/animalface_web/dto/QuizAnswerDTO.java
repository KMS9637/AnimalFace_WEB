package com.project.animalface_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class QuizAnswerDTO {
    private Long quizAnswerNo;  // 정답 번호
    private String correctAnswer;  // 정답 텍스트
}