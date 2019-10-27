package com.example.library.app_service;

import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {
    LoanService target;
    @Mock
    LendingRecordRepository lendingRecordRepository;

    @BeforeEach
    void setup() {
        target = new LoanServiceImpl(lendingRecordRepository);
    }

    @Nested
    class lent {
        @Test
        void lent() {
            String isbn = "9784567890123";
            String userId = "1234567";

            doNothing().when(lendingRecordRepository).register(any());

            target.lent(isbn, userId);
            verify(lendingRecordRepository).register(any());
        }
    }

    @DisplayName("本の返却を司書が受け取る")
    @Nested
    class receive {
        @DisplayName("貸出帳に返却記録を付ける")
        @Test
        void test_01() {
            String isbn = "9784567890123";
            String userId = "1234567";

            target.receive(isbn, userId);

            LendingRecord expected = new LendingRecord(new Isbn(isbn), userId);
            verify(lendingRecordRepository).receive(expected);
        }
    }
}