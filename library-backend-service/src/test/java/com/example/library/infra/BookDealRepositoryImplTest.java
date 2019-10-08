package com.example.library.infra;

import com.example.library.biz.BookDeal.BookDeal;
import com.example.library.biz.BookDeal.BookDealRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookDealRepositoryImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BookDealRepository bookDealRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("insert into book (isbn10) values (1234567890)");
        jdbcTemplate.execute("insert into person (person_id) values (1234567)");
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from book_deal");
        jdbcTemplate.execute("delete from book");
        jdbcTemplate.execute("delete from person");
    }

    @DisplayName("aaaa")
    @Test
    void test01() {
        List<Map<String, Object>> beforeTable = jdbcTemplate.queryForList("select * from book_deal");
        assertThat(beforeTable.size()).isZero();
        // arrange
        final String isbn10 = "1234567890";
        final String personId = "1234567";
        BookDeal bookDeal = new BookDeal(isbn10, personId);
        // act

        bookDealRepository.save(bookDeal);
        // asert
        List<Map<String, Object>> table = jdbcTemplate.queryForList("select * from book_deal");

        assertThat(table.size()).isOne();

    }
}