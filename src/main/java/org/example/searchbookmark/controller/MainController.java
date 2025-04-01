package org.example.searchbookmark.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.BookmarkService;
import org.example.searchbookmark.service.SearchService;
import org.example.searchbookmark.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final SearchService searchService;
    private final MyLogger logger = new MyLogger(MainController.class.getName());
    private final BookmarkService bookmarkService;

    public MainController(SearchService searchService, BookmarkService bookmarkService) {
        this.searchService = searchService;
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword, HttpSession session) throws IOException, InterruptedException {
        logger.info("Searching for " + keyword);
        if (keyword != null) {
            List<KeywordSearch> result = searchService.searchByKeyword(keyword);
            Map<String, KeywordSearch> map = new HashMap<>();
            for (KeywordSearch keywordSearch : result) {
                map.put(keywordSearch.uuid(), keywordSearch);
            }
            session.setAttribute("temp", map);
            model.addAttribute("result", result);
        }
        return "index";
    }

    @PostMapping("/bookmark")
    public String bookmark(@RequestParam("uuid") String uuid, Model model, HttpSession session) throws Exception {
        Map<String, KeywordSearch> temp = (HashMap<String, KeywordSearch>) session.getAttribute("temp");
        bookmarkService.createBookmark(temp.get(uuid));
        return "redirect:/"; // servlet으로 보내기
    }
}
