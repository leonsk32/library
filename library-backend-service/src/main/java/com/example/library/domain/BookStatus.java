package com.example.library.domain;

import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 本の一覧
 *
 * なんとなく、名前はイマイチ。
 * 本 + 貸出状況 = ？？？
 * なんてドメイン名？
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class BookStatus {
    Book book;
    LendingRecord lendingRecord;
}
