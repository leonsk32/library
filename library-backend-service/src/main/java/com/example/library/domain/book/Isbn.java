package com.example.library.domain.book;

import lombok.*;

/**
 * 10桁のisbnが入ってきたとき、13桁のISBNに変換して生成する
 * VO?
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

    /**
     * ISBNを10桁から13桁に変更する。
     * 変更方法としては、
     * 1. ISBN10の1桁目を除く。(10桁から9桁)
     * 2. 除いたISBNの頭に”978”を追加する
     * 3. 奇数桁 * 1 + 偶数桁 * 3を足したものを10で割り、追加する。10の場合は追加しない。
     *
     * @param isbn ISBN10
     * @return ISBN13
     */
    private String toIsbn13(String isbn) {
        String isbn13 = PREFIX_ISBN + isbn.substring(0, 9);

        int multiple;
        int checkDigit = 0;
        for (int i = 0; i < isbn13.length(); i++) {
            multiple = i % 2 == 0 ? 1 : 3;
            checkDigit += Integer.parseInt(String.valueOf(isbn13.charAt(i))) * multiple;
        }
        checkDigit = 10 - (checkDigit % 10);

        if (checkDigit == 10) {
            return isbn13 + 0;
        } else {
            return isbn13 + checkDigit;
        }
    }

    private boolean checkIsbn10(String isbn) {
        return isbn.length() == 10;
    }
}
