package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {
    private LoanService target;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LendingRecordRepository lendingRecordRepository;

    @BeforeEach
    void setup() {
        target = new LoanServiceImpl(bookRepository, userRepository, lendingRecordRepository);
    }

    @Nested
    class lent {
        @Test
        void lent() {
            String isbn = "9784567890978";
            String userId = "9784567";

            when(bookRepository.findById(any())).thenReturn(new Book("9784567890978", "titleA"));
            when(userRepository.findById(any())).thenReturn(new User("9784567", "aa@bb"));
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
            String isbn = "9784567890978";
            String userId = "9784567";

            doNothing().when(lendingRecordRepository).delete(any());
            target.receive(isbn, userId);

            LendingRecord expected = new LendingRecord(new Book(isbn, "titleA"), new User(userId, "aa@bb"));
            verify(lendingRecordRepository).delete(any());
        }
    }
}