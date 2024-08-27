package com.project.animalface_web.controller.ohjcontroller;

import com.project.animalface_web.service.ohjserviece.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    // 검색 페이지 로딩
    @GetMapping("/search")
    public String showSearchPage() {
        return "search";  // search.html 뷰를 반환
    }
}