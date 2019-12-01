package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.ranking.RankingList;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RankingApi.class)
class RankingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @DisplayName("全検索")
    @Test
    void findBookRanking() throws Exception {
        List<Ranking> rankings = List.of(
                new Ranking("1234567", "きり丸", 10),
                new Ranking("1234568", "乱太郎", 7),
                new Ranking("1234569", "新兵衛", 3));

        RankingList rankingList = new RankingList(rankings);

        when(service.searchLentRanking()).thenReturn(rankingList);

        @Language("JSON") String content = "{\"rankings\": [\n" +
                "  " +
                "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"name\": \"きり丸\",\n" +
                "  \"num\": 10\n" +
                "},\n" +
                "  {\n" +
                "    \"userId\": \"1234568\",\n" +
                "    \"name\": \"乱太郎\",\n" +
                "    \"num\": 7\n" +
                "  " +
                "},\n" +
                "  {\n" +
                "    \"userId\": \"1234569\",\n" +
                "    \"name\": \"新兵衛\",\n" +
                "    \"num\": 3\n" +
                "  " +
                "}\n" +
                "]}";

        mockMvc.perform(get("/v1/ranking/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(content, true))
        ;
        verify(service).searchLentRanking();
    }
}