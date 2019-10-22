package com.example.library.domain;

import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSearchComponent {
    private final LendingRecordRepository lendingRecordRepository;

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
