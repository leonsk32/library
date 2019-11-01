package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryImplTest {

    @Autowired
    BookRepository target;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from BOOK");
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("Delete from BOOK");
    }

    @DisplayName("取得できるケース")
    @Test
    void test01() {
        jdbcTemplate.execute("insert into BOOK (isbn) values('9784567890978')");
        String isbn = "9784567890978";
        Book book = target.findById(isbn);

        assertThat(book).isEqualTo(new Book(isbn));
    }

    @DisplayName("取得できないケース")
    @Test
    void test02() {
        String isbn = "9784567890978";
        Book book = target.findById("9784567890124");
        assertThat(book).isNull();
    }

    @DisplayName("全取得")
    @Test
    void test03() {
        jdbcTemplate.execute("insert into BOOK (isbn) values('9784567890978')");
        jdbcTemplate.execute("insert into BOOK (isbn) values('9784567890979')");
        List<Book> books = target.findAll();
        assertThat(books).isEqualTo(asList(
                new Book("9784567890978"),
                new Book("9784567890979")
        ));
    }
}
