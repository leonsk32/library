package com.example.library.app_service;

import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * application Serviceの可能性が高い
 */
@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    private final LendingRecordRepository lendingRecordRepository;
    @Override
    public void lent(String isbn, String userId) {
        LendingRecord record = new LendingRecord(null, userId);
        lendingRecordRepository.register(record);
    }
}
