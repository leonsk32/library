package com.example.library.functionalTest;

import com.example.library.restapi.BookDealDto;
import com.example.library.restapi.LibraryController;
import com.example.library.restapi.LibraryExceptionHandler;
import com.example.library.service.BorrowService;
import com.example.library.util.DbBookshelfUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class LibraryControllerTest {

    @Autowired
    LibraryController libraryController;

    @Autowired
    ObjectMapper mapper;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
//        dbBookshelfUtils.deleteAll("BOOK");
        mvc = MockMvcBuilders
                .standaloneSetup(libraryController)
                .setControllerAdvice(new LibraryExceptionHandler())
                .build();
    }

    @DisplayName("１冊の本を借りる")
    @Test
    void test01() throws Exception {

        // arrange
         BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("1234567890")
                .personId("1234567")
                .build();
        String request = mapper.writeValueAsString(bookDealDto);

        // act and assert
        mvc.perform(post("/v1/book_deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(status().isOk());

//        assertThat(dbBookshelfUtils.getAmountFor(isbn10)).isEqualTo(0);
    }

}
