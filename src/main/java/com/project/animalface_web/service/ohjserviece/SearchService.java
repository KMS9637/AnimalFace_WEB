package com.project.animalface_web.service.ohjserviece;// SearchService.java
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    public List<String> performSearch(String query) {
        // 실제 검색 로직 구현
        return new ArrayList<>(); // 임시 결과 반환
    }
}