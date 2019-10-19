package com.example.library.app_service;

import com.example.library.domain.lending.LendingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {
    LibrarianService target;
    @Mock
    LendingRecordRepository lendingRecordRepository;

    @BeforeEach
    void setup() {
        target = new LibrarianServiceImpl(lendingRecordRepository);
    }

    @Test
    void lent() {
        String isbn = "1234567890";
        String userId = "1234567";

        doNothing().when(lendingRecordRepository).regist(any());

        target.lent(isbn, userId);
        verify(lendingRecordRepository).regist(any());
    }
}