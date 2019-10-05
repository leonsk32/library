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
        Book book = bookshelf.takeOut(isbn10);

        // assert
        assertThat(book.isbn10()).isEqualTo(isbn10);
        assertThat(bookshelf.get(isbn10).amount()).isEqualTo(1);
    }
}
