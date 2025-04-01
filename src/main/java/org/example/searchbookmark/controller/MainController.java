package org.example.searchbookmark.controller;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {

    private final SearchService searchService;

    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String index(Model model) throws IOException, InterruptedException {
        List<KeywordSearch> result = searchService.searchByKeyword("4월은 너의 거짓말");
        model.addAttribute("result", result);
        return "index";
    }
}
