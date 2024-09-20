package com.project.animalface_web.repository;

import com.project.animalface_web.domain.game.Game;
import com.project.animalface_web.domain.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // 필요한 커스텀 쿼리 메소드가 있을 경우 여기에 작성
}