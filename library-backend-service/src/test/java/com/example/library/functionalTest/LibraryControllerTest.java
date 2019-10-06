package com.example.library.functionalTest;

import com.example.library.restapi.LibraryController;
import com.example.library.restapi.LibraryExceptionHandler;
import com.example.library.service.BookshelfService;
import com.example.library.util.DbBookshelfUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class LibraryControllerTest {

    @Autowired
    BookshelfService service;
    @Autowired
    DbBookshelfUtils dbBookshelfUtils;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        dbBookshelfUtils.deleteAll("BOOK");
        mvc = MockMvcBuilders
                .standaloneSetup(new LibraryController(service))
                .setControllerAdvice(new LibraryExceptionHandler())
                .build();
    }

    @DisplayName("１冊の本を借りる")
    @Test
    void test01() throws Exception {

        // arrange
        final String isbn10 = "1111111111";
        dbBookshelfUtils.insertBook(isbn10, 1);

        // act and assert
        mvc.perform(patch("/v1/books/" + isbn10))
                .andExpect(status().isOk());

        assertThat(dbBookshelfUtils.getAmountFor(isbn10)).isEqualTo(0);
    }

    @DisplayName("ない本を借りようとしてエラー")
    @Test
    void test02() throws Exception {
        // act and assert
        String isbn10 = "1111111111";
        MvcResult result = mvc.perform(patch("/v1/books/" + isbn10))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo("No book with isbn10 : isbn10 = 1111111111");
    }

    @DisplayName("全部借りられている本を借りようとしてエラー")
    @Test
    void test03() throws Exception {
        // arrange
        String isbn10 = "1111111111";
        dbBookshelfUtils.insertBook(isbn10, 0);

        // act and assert
        MvcResult result = mvc.perform(patch("/v1/books/" + isbn10))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo("the book amount is 0: isbn10 = 1111111111");
    }
}
