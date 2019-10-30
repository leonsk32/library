package com.example.library.app_service;

import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LendingRecordsServiceImpl implements LendingRecordsService {
    private final LendingRecordRepository lendingRecordRepository;

    @Override
    public List<LendingRecord> search(String... options) {
        return lendingRecordRepository.findAll();
    }

    @Override
    public void borrow(String isbn, String userId) {

    }

    @Override
    public void returnn(String isbn, String userId) {

    }
}
