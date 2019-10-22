package com.example.library.restapi.books;

import com.example.library.app_service.LibrarianService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksRestController.class)
class BooksRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LibrarianService service;

    @DisplayName("検索する")
    @Test
    void test_01() throws Exception {
        mvc.perform(
                get("/v1/books")
        )
                .andExpect(status().isNotImplemented());
    }
}