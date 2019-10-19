package com.example.library.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Isbn isbnEntity = null;

        when(lendingRecord.create(isbnEntity, userId)).thenReturn(lendingRecord);

        target.lent(isbn, userId);
        verify(lendingRecord).lent();
    }
}