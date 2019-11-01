package com.example.library.domain;

import com.example.library.domain.book.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BookTest {
    private static final String EVANS_DDD_ISBN10 = "4798121967";
    private static final String EVANS_DDD_ISBN13 = "9784798121963";


    @DisplayName("同一性の操作の検証")
    @Test
    void equal01() {
        Book book1 = new Book(EVANS_DDD_ISBN10);
        Book book2 = new Book(EVANS_DDD_ISBN13);
        assertThat(book1).isEqualTo(book2);
    }
}