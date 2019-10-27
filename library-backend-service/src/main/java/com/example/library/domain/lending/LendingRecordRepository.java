package com.example.library.domain.lending;


import com.example.library.domain.book.Book;
import com.example.library.domain.user.User;

import java.util.List;

public interface LendingRecordRepository {
    void register(LendingRecord lendingRecord);

    void delete(LendingRecord lendingRecord);

    List<LendingRecord> findAll();
    LendingRecord findById(Book book, User user);
}
