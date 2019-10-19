package com.example.library.biz.service;

import com.example.library.biz.domain.Isbn;
import com.example.library.biz.domain.LendingRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    @Override
    public void lent(String isbn, String userId) {
        LendingRecord record = new LendingRecord(new Isbn(isbn), userId);
        record.lent();
    }
}
