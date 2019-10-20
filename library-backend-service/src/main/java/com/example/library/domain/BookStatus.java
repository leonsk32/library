package com.example.library.domain;

import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import lombok.EqualsAndHashCode;

/**
 * 本の一覧
 *
 * なんとなく、名前はイマイチ。
 * 本 + 貸出状況 = ？？？
 * なんてドメイン名？
 */
@EqualsAndHashCode
public class BookStatus {
    Book book;
    LendingRecord lendingRecord;
}
