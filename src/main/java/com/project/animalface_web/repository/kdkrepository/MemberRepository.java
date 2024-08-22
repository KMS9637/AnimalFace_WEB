package com.project.animalface_web.repository.kdkrepository;

import com.project.animalface_web.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 소셜 로그인이 아닌, 일반 로그인으로 검색하기.
    // N+1,한번에 다같이 조회를 하자. in 연산자 이용해서, 하나의 쿼리로 동작하기.
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.memberId = :mid and m.social = false ")
    Optional<Member> getWithRoles(String mid);

    // 아이디로 유저 확인.
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByMemberId(Long memberId);

    //DML 적용하기
    @Modifying
    @Transactional
    @Query("update Member m set m.memberPw=:mpw where m.memberId = :mid")
    void updatePassword(@Param("mpw") String mpw, @Param("mid") String mid);


}
