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

    @DisplayName("取得できないケース")
    @Test
    void test02() {
        String isbn = "9784567890978";
        Book book = target.findById("9784567890124");
        assertThat(book).isNull();
    }

    @DisplayName("シナリオテスト" +
            "すべてのメソッドを組み合わせてテストしている")
    @Test
    void test07() {
        String isbn1 = "9781234567890";
        String isbn2 = "9781234567891";
        String isbn3 = "9781234567892";
        Book book1 = new Book(isbn1);
        Book book2 = new Book(isbn2);
        Book book3 = new Book(isbn3);

        target.register(book1);
        target.register(book2);
        target.register(book3);

        Book byId = target.findById(isbn1);
        assertThat(byId).isNotNull();
        assertThat(byId.getAmount()).isOne();

        List<Book> all = target.findAll();
        Book book4 = all.get(0);
        Book book5 = all.get(1);
        Book book6 = all.get(2);
        assertThat(all.size()).isEqualTo(3);

        target.delete(book5);
        List<Book> all2 = target.findAll();
        assertThat(all2.size()).isEqualTo(2);

        book4.add();
        book4.add();
        book6.add();
        target.save(book4);
        target.save(book6);

        List<Book> all1 = target.findAll();
        String isbn9 = all1.get(0).getIsbn();
        Book byId1 = target.findById(isbn9);
        assertThat(byId1).isNotNull();

    }
}
