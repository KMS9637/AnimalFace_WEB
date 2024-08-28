package com.project.animalface_web.controller;

import com.project.animalface_web.service.ohjserviece.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // 검색 페이지 로딩
    @GetMapping("/search")
    public String showSearchPage() {
        return "search";  // search.html 뷰를 반환
    }

    // 검색 결과 처리 (GET 방식)
    @GetMapping("/searchResults")
    public String handleSearch(@RequestParam("q") String query, Model model) {
        // 검색 결과 가져오기
        List<String> searchResults = searchService.performSearch(query);

        // 모델에 검색 결과 추가
        model.addAttribute("results", searchResults);

        // 검색 결과 페이지 반환
        return "searchResults";  // searchResults.html 뷰를 반환
    }
}