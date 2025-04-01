package org.example.searchbookmark.controller;

import org.example.searchbookmark.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {

    private final SearchService searchService;

    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String index() throws IOException, InterruptedException {
        searchService.searchByKeyword("4월은 너의 거짓말");
        return "index";
    }
}
