package com.example.library.service;

import com.example.library.biz.bookshelf.InMemoryBookshelfRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * APPサービス
 */
class BookshelfServiceTest {

    @DisplayName("本を借りるユースケース")
    @Test
    void test01() {
        // arrange
        InMemoryBookshelfRepository bookshelfRepository = new InMemoryBookshelfRepository();
        BookshelfService bookshelfService = new BookshelfService(bookshelfRepository);
        final String isbn10 = "1111111111";

        // act
        bookshelfService.borrow(isbn10);

        // assert
        int amount = bookshelfRepository.get().getAmount(isbn10);
        assertThat(amount).isEqualTo(0);
    }
}
