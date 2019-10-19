package com.example.library.restapi.books;

import com.example.library.biz.service.LibrarianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BooksIsbnActionsRestController.class)
class BooksIsbnActionsRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    LibrarianService service;

    @Test
    void borrow_test01() throws Exception {
        // arrange
        final String isbn = "1111111111";
        String userId = "1234567";

        //language=JSON
        String body = "{" +
                "\"type\":\"borrow\"," +
                "\"userId\":\"1234567\"" +
                "}";

        // act and assert
        mvc.perform(
                post("/v1/books/" + isbn + "/actions")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(body)
        )
                .andExpect(status().isOk());
        verify(service).lent(isbn, userId);
    }
}