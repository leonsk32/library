package com.example.library.biz.bookshelf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookshelfTest {

    @DisplayName("本棚に一冊の本をいれる")
    @Test
    void test01() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepository();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        // act
        bookshelf.putBook(isbn10);
        // assert
        assertThat(bookshelf.get(isbn10).amount()).isEqualTo(1);
    }

    @DisplayName("本棚に本がないとき、RuntimeExceptionをはく")
    @Test
    void test02() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepository();
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
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepository();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        bookshelf.putBook(isbn10);
        bookshelf.putBook(isbn10);

        // act
        bookshelf.takeOut(isbn10);

        // assert
        assertThat(bookshelf.get(isbn10).amount()).isEqualTo(1);
    }

    @DisplayName("本の格納と取り出しのテスト")
    @Test
    void test04() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepository();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String DDDIsbn10 = "4798121967";
        final String SpringBootIsbn10 = "4774182176";
        final String MustleIsbn10 = "1234567891";
        bookshelf.putBook(DDDIsbn10, 2);
        bookshelf.putBook(SpringBootIsbn10, 2);
        bookshelf.putBook(MustleIsbn10, 4);

        // act
        bookshelf.takeOut(DDDIsbn10);
        bookshelf.takeOut(SpringBootIsbn10);
        bookshelf.takeOut(MustleIsbn10);
        bookshelf.takeOut(MustleIsbn10);
        bookshelf.takeOut(MustleIsbn10);

        // assert
        assertThat(bookshelf.get(DDDIsbn10).amount()).isEqualTo(1);
        assertThat(bookshelf.get(SpringBootIsbn10).amount()).isEqualTo(1);
        assertThat(bookshelf.get(MustleIsbn10).amount()).isEqualTo(1);
    }


    @DisplayName("ない本を借りようとしたらRuntimeExceptionをはく")
    @Test
    void test05() {
        // arrange
        BookshelfRepository bookShelfRepository = new InMemoryBookshelfRepository();
        Bookshelf bookshelf = bookShelfRepository.get();
        final String isbn10 = "4798121967";
        // act and assert
        assertThatThrownBy(() -> bookshelf.takeOut(isbn10))
                .isInstanceOfSatisfying(RuntimeException.class,
                        e -> e.getMessage().equals("その本の０冊しかない:isbn10" + isbn10));
    }
}
