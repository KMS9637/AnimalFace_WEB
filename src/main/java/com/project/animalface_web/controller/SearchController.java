package com.project.animalface_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    // 검색 페이지 로딩
    @GetMapping("/search")
    public String showSearchPage() {
        return "search";  // search.html 뷰를 반환
    }
}