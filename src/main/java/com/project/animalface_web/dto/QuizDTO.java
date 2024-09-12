package com.project.animalface_web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private Long quizNo;  // 퀴즈 번호
    private String quizName;  // 퀴즈 이름
    private List<QuizQuestionDTO> questions;  // 질문 목록
    private int correctAnswerCount;  // 정답 개수
}