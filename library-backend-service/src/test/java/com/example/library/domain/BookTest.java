package com.example.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BookTest {

    @DisplayName("同一性の操作の検証")
    @Test
    public void equal01() {
        Book book1 = new Book(new Isbn("1234567890", "1234567890123"), "tile2");
        Book book2 = new Book(new Isbn("1234567890", "1234567890123"), "title3");
        assertThat(book1).isEqualTo(book2);
    }
}