package com.project.animalface_web.repository;

import com.project.animalface_web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
