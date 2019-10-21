package com.example.library.app_service;

import com.example.library.domain.BookStatus;
import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * application Serviceの可能性が高い
 */
@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    private final LendingRecordRepository lendingRecordRepository;
    @Override
    public void lent(String isbn, String userId) {
        Isbn isbnEntity = new Isbn(isbn);
        LendingRecord record = new LendingRecord(isbnEntity, userId);
        lendingRecordRepository.register(record);
    }

    @Override
    public void receive(String isbn, String userId) {
        Isbn isbnEntity = new Isbn(isbn);
        LendingRecord record = new LendingRecord(isbnEntity, userId);
        lendingRecordRepository.receive(record);
    }

    @Override
    public List<BookStatus> search(String... options) {
        // bookRepositoryとLendingRecordRepositoryから取得してくっつけるのと、
        // 最初からくっついた状態で取得するのはどっちがいいんだろう。
        return Arrays.asList();
    }
}
