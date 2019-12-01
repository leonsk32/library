package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.ranking.Ranking;
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
        when(service.searchLentRanking()).thenReturn(List.of(
              new Ranking("1234567","きり丸",10),
                new Ranking("1234568","乱太郎",7),
                new Ranking("1234569","新兵衛",3)
        ));
        mockMvc.perform(get("/v1/ranking/books"))
                .andExpect(status().isNotImplemented())
                ;
        verify(service).searchLentRanking();
    }
}