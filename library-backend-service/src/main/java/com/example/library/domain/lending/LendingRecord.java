package com.example.library.domain.lending;


import com.example.library.domain.book.Book;
import com.example.library.domain.user.User;
import lombok.Getter;

/**
 * こいつはEntity
 * 日付を持ったほうがよいかも
 * immutable(変更不可)
 */
public final class LendingRecord {
    @Getter
    private final Book book;
    @Getter
    private final User user;

    public LendingRecord(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    // TODO 同一性を考える上で日付を持つべきという考えも
    // 実装上、LocalDateTimeをコンストラクタインジェクション無しで使いたかったが
    // よくわからなかったのでヘルプください
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LendingRecord)) throw new RuntimeException("Lending Record同士で比較してくれ");
        LendingRecord otherLendingRecord = (LendingRecord) other;
        return this.book.equals(otherLendingRecord.book) &&
                this.user.equals(otherLendingRecord.user);
    }

}
