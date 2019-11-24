package com.example.library.restapi;

import com.example.library.app_service.BookService;
import com.example.library.domain.book.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Books.class)
class BooksTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService service;

    @DisplayName("本の全件検索")
    @Test
    void test01() throws Exception {
        // arrange
        List<Book> books = asList(
                new Book("9784567890123"),
                new Book("9784567890124"));

        // act and assert
        when(service.searchAll()).thenReturn(books);

        mockMvc.perform(get("/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"isbns\": [\n" +
                                "    \"9784567890123\",\n" +
                                "    \"9784567890124\"\n" +
                                "  ]\n" +
                                "}", true));
    }

    @DisplayName("本の登録")
    @Test
    void test02() throws Exception {
        // arrange
        final String isbn = "9784567890123";
        doNothing().when(service).register(isbn);

        // act and assert
        mockMvc.perform(put("/v1/books/" + isbn)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(service).register(isbn);
    }

    @DisplayName("本の排棄")
    @Test
    void test03() throws Exception {
        // arrange
        final String isbn = "9784567890123";
        // act and assert
        doNothing().when(service).waste(isbn);
        mockMvc.perform(delete("/v1/books/" + isbn)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(service).waste(isbn);
    }
}