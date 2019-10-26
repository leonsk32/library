package com.example.library.app_service;

import com.example.library.domain.BookStatus;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {
    private final LendingRecordRepository lendingRecordRepository;

    @Override
    public List<BookStatus> search(String... options) {

        List<LendingRecord> lendingRecords = lendingRecordRepository.find();
        List<BookStatus> bookStatusList = new ArrayList<>();

        for (LendingRecord lendingRecord : lendingRecords) {
            BookStatus bookStatus = new BookStatus(null, lendingRecord);
            bookStatusList.add(bookStatus);
        }

        return bookStatusList;
    }
}
