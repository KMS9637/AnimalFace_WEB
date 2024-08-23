package com.project.animalface_web.controller.ohjcontroller;

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

    // 실제 검색 로직을 수행하는 서비스 (가정)
    private SearchService searchService = new SearchService();

    // SearchService 내 performSearch 메서드 구현 예
    public class SearchService {
        public List<String> performSearch(String query) {
            // 실제 검색 로직 구현
            return new ArrayList<>(); // 임시 결과 반환
        }
    }
}

