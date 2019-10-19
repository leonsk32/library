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
    LendingRecord lendingRecord;

    @BeforeEach
    void setup() {
        target = new LibrarianServiceImpl(lendingRecord);
    }

    @Test
    void lent() {
        String isbn = "1234567890";
        String userId = "1234567";
        Isbn isbnEntity = new Isbn(isbn);

        when(lendingRecord.create(isbnEntity, userId)).thenReturn(lendingRecord);

        target.lent(isbn, userId);
        verify(lendingRecord).lent();
    }
}