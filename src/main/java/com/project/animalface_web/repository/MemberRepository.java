package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByMemberId(String memberId);

    void deleteByMemberNo(Long memberNo);
@Modifying
@Transactional
@Query("DELETE FROM Member m WHERE m.memberId = :memberId")
void deleteByMemberId(@Param("memberId") String memberId);
}
