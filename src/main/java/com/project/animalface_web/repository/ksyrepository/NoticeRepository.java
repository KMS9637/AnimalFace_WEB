package com.project.animalface_web.repository.ksyrepository;

import com.project.animalface_web.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
