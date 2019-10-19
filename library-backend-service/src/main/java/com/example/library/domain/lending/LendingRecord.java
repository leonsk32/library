package com.example.library.domain.lending;


import com.example.library.domain.book.Isbn;
import lombok.Getter;

/**
 * こいつはEntityの可能性がある
 * 日付を持ったほうがよいかも
 */
public class LendingRecord {
    @Getter
    Isbn isbn;
    @Getter
    String userId;

    public LendingRecord(Isbn isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }
}
