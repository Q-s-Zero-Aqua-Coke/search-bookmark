package org.example.searchbookmark.util;

import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.model.vo.NaverSearchParam;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class NaverSearchAPI implements DotenvMixin, ObjectMapperMixin {
    private final MyLogger logger = new MyLogger(this.getClass().getName());
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<KeywordSearch> callAPI(NaverSearchParam param) throws IOException, InterruptedException {
        String url = "https://openapi.naver.com/v1/search/blog.json";
        String query = URLEncoder.encode(param.query(), StandardCharsets.UTF_8);
        String urlWithQuery = "%s?query=%s".formatted(url, query);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlWithQuery))
                .header("X-Naver-Client-Id", dotenv.get("NAVER_CLIENT_ID"))
                .header("X-Naver-Client-Secret", dotenv.get("NAVER_CLIENT_SECRET"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        logger.info(responseBody);
        return List.of();
    }
}
