package com.example.library.restapi.books;

import com.example.library.app_service.LoanService;
import com.example.library.domain.BookStatus;
import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksRestController.class)
class BooksRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LoanService service;

    @DisplayName("検索する")
    @Test
    void test_01() throws Exception {
        List<BookStatus> bookStatusList =
                Arrays.asList(
                        new BookStatus(null, new LendingRecord(new Isbn("1234567890123"), "123")),
                        new BookStatus(null, new LendingRecord(new Isbn("1234567890124"), "456"))
                );

        when(service.search()).thenReturn(bookStatusList);

        mvc.perform(
                get("/v1/books")
        )
                .andExpect(status().isOk())
        .andExpect(content().json("{\"books\":[{\"isbn\":\"1234567890123\",\"userId\":\"123\"},{\"isbn\":\"1234567890124\",\"userId\":\"456\"}]}", true))
        ;
    }
}