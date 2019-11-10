package com.example.library.restapi;

import com.example.library.app_service.BookService;
import com.example.library.domain.book.Book;
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

    @Test
    void test01() throws Exception {
        List<Book> books = asList(
                new Book("9784567890123"),
                new Book("9784567890124"));

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

    @Test
    void test02() throws Exception {
        final String isbn = "9784567890123";
        doNothing().when(service).register(isbn);

        mockMvc.perform(put("/v1/books/" + isbn)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(service).register(isbn);
    }

    @Test
    void test03() throws Exception {
        final String isbn = "9784567890123";
        doNothing().when(service).waste(isbn);

        mockMvc.perform(delete("/v1/books/" + isbn)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(service).waste(isbn);
    }
}