package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * VO?
 * TODO isbn10とisbn13を持つことが必要
 */
public class Isbn {
    private final String isbn10;
    private final String isbn13;

    public Isbn(String isbn10, String isbn13) {
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
    }

    public String getIsbn() {
        return isbn10 + isbn13;
    }
}
