package com.example.library.restapi;

import com.example.library.service.BookshelfService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO webMVCTestで実現する方法がわからないのでHELP
@SpringBootTest
class LibraryControllerTest {

    @Autowired
    BookshelfService service;
    @Autowired
    JdbcTemplate jdbcTemplate;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("delete from book");
        mvc = MockMvcBuilders.standaloneSetup(new LibraryController(service)).build();
    }

    @DisplayName("１冊の本を借りる")
    @Test
    void sample() throws Exception {

        // arrange
        jdbcTemplate.execute("insert into book(isbn10, amount) values('1111111111',1)");

        // act and assert
        mvc.perform(patch("/v1/books/1111111111"))
                .andExpect(status().isOk());

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from BOOK");
        assertThat(result.size()).isEqualTo(1);
        assertThat((String)result.get(0).get("ISBN10")).isEqualTo("1111111111");
        assertThat((Integer)result.get(0).get("AMOUNT")).isEqualTo(0);
    }
}
