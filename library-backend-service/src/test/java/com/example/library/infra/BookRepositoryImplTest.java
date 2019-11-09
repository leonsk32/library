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

    @DisplayName("登録")
    @Test
    void test04() {
        List<Book> none = target.findAll();
        assertThat(none.size()).isZero();

        target.register(new Book("9781234567890"));

        List<Book> all = target.findAll();
        assertThat(all.size()).isOne();
        assertThat(all.get(0).getIsbn()).isEqualTo("9781234567890");
        assertThat(all.get(0).getAmount()).isEqualTo(1);

    }

    @DisplayName("削除")
    @Test
    void test05() {
        Book book = new Book("9781234567890");
        target.register(book);
        List<Book> all = target.findAll();
        assertThat(all.size()).isOne();
        assertThat(all.get(0).getIsbn()).isEqualTo("9781234567890");
        assertThat(all.get(0).getAmount()).isEqualTo(1);

        target.delete(book);

        List<Book> none = target.findAll();
        assertThat(none.size()).isZero();
    }

    @DisplayName("保存")
    @Test
    void test06() {
        String isbn = "9781234567890";
        Book book = new Book(isbn);
        target.register(book);

        Book byId = target.findById(isbn);
        byId.add();

        target.save(byId);
        List<Book> all = target.findAll();
        assertThat(all.size()).isOne();
        assertThat(all.get(0).getIsbn()).isEqualTo("9781234567890");
        assertThat(all.get(0).getAmount()).isEqualTo(2);

    }

    @DisplayName("シナリオテスト")
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
