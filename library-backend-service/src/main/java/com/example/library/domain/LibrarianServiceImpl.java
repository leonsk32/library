package com.example.library.domain;

import com.example.library.domain.Isbn;
import com.example.library.domain.LendingRecord;
import com.example.library.domain.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    private final LendingRecord lendingRecord;

    @Override
    public void lent(String isbn, String userId) {
        LendingRecord record = lendingRecord.create(new Isbn(isbn), userId);
        record.lent();
    }
}
