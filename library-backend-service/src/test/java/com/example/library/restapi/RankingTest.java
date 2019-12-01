package com.example.library.restapi;

import com.example.library.app_service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RankingApi.class)
class RankingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @DisplayName("全検索")
    @Test
    void findBook() throws Exception {
//        when(service.searchAll()).thenReturn(List.of(
//                new User("1234567", "aa@bb", "kirima", "nainai"),
//                new User("1234568", "cc@bb", "ki", "na")
//        ));
//        mockMvc.perform(get("/v1/ranking/books"))
//                .andExpect(status().isNotImplemented())
//                ;
//        verify(service).searchAll();
    }
}