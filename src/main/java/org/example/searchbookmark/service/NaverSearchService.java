package org.example.searchbookmark.service;

import org.example.searchbookmark.model.vo.NaverSearchParam;
import org.example.searchbookmark.util.MyLogger;
import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.util.NaverSearchAPI;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NaverSearchService implements SearchService {

    final MyLogger logger = new MyLogger(this.getClass().getName());
    private final NaverSearchAPI naverSearchAPI;

    public NaverSearchService(NaverSearchAPI naverSearchAPI) {
        this.naverSearchAPI = naverSearchAPI;
    }

    @Override
    public List<KeywordSearch> searchByKeyword(String keyword) throws IOException, InterruptedException {
        return naverSearchAPI.callAPI(new NaverSearchParam(keyword));
    }
}
