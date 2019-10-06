package com.example.library.biz.bookshelf;

import com.example.library.infra.InMemoryBookshelfRepositoryForUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookshelfTest {

    @DisplayName("本棚に一冊の本をいれる")
    @Test
    void test01() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepositoryForUT();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        // act
        bookshelf.registBook(isbn10);
        // assert
        assertThat(bookshelf.get(isbn10).amount()).isEqualTo(1);
    }

    @DisplayName("本棚に本がないとき、RuntimeExceptionをはく")
    @Test
    void test02() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepositoryForUT();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        // act and assert
        assertThatThrownBy(() -> bookshelf.get(isbn10).amount())
                .isInstanceOfSatisfying(RuntimeException.class,
                        e -> e.getMessage().equals("そのISBNの本がねえ" + isbn10));
    }

    @DisplayName("「エリック・エヴァンスのドメイン駆動開発」が２冊あるを本棚から" +
            "１冊とる" +
            "ISBN-10: 4798121967" +
            " ISBN-13: 978-4798121963")
    @Test
    void test03() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepositoryForUT();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        bookshelf.registBook(isbn10);
        bookshelf.registBook(isbn10);

        // act
        bookshelf.takeOut(isbn10);

        // assert
        assertThat(bookshelf.get(isbn10).amount()).isEqualTo(1);
    }

    @DisplayName("本の格納と取り出しと返却のテスト")
    @Test
    void test04() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepositoryForUT();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String DDDIsbn10 = "4798121967";
        final String SpringBootIsbn10 = "4774182176";
        final String MustleIsbn10 = "1234567891";

        // act
        bookshelf.registBooks(DDDIsbn10, 2);
        bookshelf.registBooks(SpringBootIsbn10, 2);
        bookshelf.registBooks(MustleIsbn10, 4);

        bookshelf.takeOut(DDDIsbn10);
        bookshelf.takeOut(SpringBootIsbn10);
        bookshelf.takeOut(MustleIsbn10);
        bookshelf.takeOut(MustleIsbn10);
        bookshelf.takeOut(MustleIsbn10);

        bookshelf.returnBook(MustleIsbn10);

        // assert
        assertThat(bookshelf.get(DDDIsbn10).amount()).isEqualTo(1);
        assertThat(bookshelf.get(SpringBootIsbn10).amount()).isEqualTo(1);
        assertThat(bookshelf.get(MustleIsbn10).amount()).isEqualTo(2);
    }


    @DisplayName("ない本を借りようとしたらRuntimeExceptionをはく")
    @Test
    void test05() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepositoryForUT();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        // act and assert
        assertThatThrownBy(() -> bookshelf.takeOut(isbn10))
                .isInstanceOfSatisfying(RuntimeException.class,
                        e -> e.getMessage().equals("その本の０冊しかない:isbn10" + isbn10));
    }
}
