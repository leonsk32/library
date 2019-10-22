package com.example.library.domain.lending;


import com.example.library.domain.book.Isbn;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * こいつはEntityの可能性がある
 * 日付を持ったほうがよいかも
 */
@Data
@NoArgsConstructor
public class LendingRecord {
    Isbn isbn;
    String userId;

    public LendingRecord(Isbn isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }
}
