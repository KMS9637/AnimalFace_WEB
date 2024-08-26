package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // memberId로 회원을 조회하는 메서드
    Member findByMemberId(String memberId);
}
