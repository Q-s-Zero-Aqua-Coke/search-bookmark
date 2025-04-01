package org.example.searchbookmark.controller;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.SearchService;
import org.example.searchbookmark.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private final SearchService searchService;
    private final MyLogger logger = new MyLogger(MainController.class.getName());

    public MainController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword) throws IOException, InterruptedException {
        logger.info("Searching for " + keyword);
        if (keyword != null) {
            List<KeywordSearch> result = searchService.searchByKeyword(keyword);
            model.addAttribute("result", result);
        }
        return "index";
    }

//    @PostMapping("/bookmark")
//    public String bookmark(@ModelAttribute )
}
