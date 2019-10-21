package com.example.library.domain.book;

import lombok.*;

/**
 * 10桁のisbnが入ってきたとき、13桁のISBNに変換して生成する
 *
 * VO?
 * TODO isbn10とisbn13を持つことが必要
 */
@EqualsAndHashCode
@Getter
public class Isbn {
    private final String isbn;

    public Isbn(String isbn) {
        this.isbn = setIsbn(isbn);
    }

    private String setIsbn(String isbn) {
        if (checkIsbn10(isbn)) {
            return toIsbn13(isbn);
        }

        if (checkIsbn13(isbn)) {
            return isbn;
        }

        throw new RuntimeException();
    }

    private boolean checkIsbn13(String isbn) {
        return isbn.length() == 13;
    }

    private static final String PREFIX_ISBN = "978";

    private String toIsbn13(String isbn) {
        String isbn13 = PREFIX_ISBN + isbn.substring(0, 9);

        int multiple = 0;
        int checkDigit = 0;
        for (int i = 0; i < isbn13.length(); i++) {
            multiple = i == 0 ? 1 : 3;
            checkDigit += (int) isbn13.charAt(i) * multiple;
        }
        checkDigit = 10 - (checkDigit % 10);

        return isbn13 + checkDigit;
    }

    private boolean checkIsbn10(String isbn) {
        return isbn.length() == 10;
    }
}
