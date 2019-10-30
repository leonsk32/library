package com.example.library.app_service;

import com.example.library.domain.lending.LendingRecord;

import java.util.List;

public interface LendingRecordsService {
    List<LendingRecord> search(String... options);

    void borrow(String isbn, String userId);

    void returnn(String isbn, String userId);
}
