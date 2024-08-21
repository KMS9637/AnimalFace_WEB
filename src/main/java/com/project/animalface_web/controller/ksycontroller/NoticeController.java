package com.project.animalface_web.controller.ksycontroller;

import com.project.animalface_web.dto.ksydto.PageRequestDTO;
import com.project.animalface_web.service.ksyserviece.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("responseDTO", pageRequestDTO);
    }
}
