package com.example.library.biz.service;

import com.example.library.biz.domain.Isbn;
import com.example.library.biz.domain.LendingRecord;
import com.example.library.infra.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    private final LendingRecordRepository repository;

    @Override
    public void lent(String isbn, String userId) {
        LendingRecord record = new LendingRecord(new Isbn(isbn), userId);
        repository.insert(record);
    }
}
