package com.example.library.biz.service;

import com.example.library.biz.domain.Isbn;
import com.example.library.biz.domain.LendingRecord;
import com.example.library.infra.LendingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {
    LibrarianService target;
    @Mock
    LendingRecordRepository recordRepository;

    @BeforeEach
    void setup() {
        target = new LibrarianServiceImpl(recordRepository);
    }

    @Test
    void lent() {
        String isbn = "1234567890";
        String userId = "1234567";

        LendingRecord lendingRecord = new LendingRecord(new Isbn(isbn), userId);

        target.lent(isbn, userId);
        verify(recordRepository).insert(lendingRecord);
    }
}