package com.example.library.app_service;

import com.example.library.domain.BookSearchComponent;
import com.example.library.domain.BookStatus;
import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {
    LibrarianService target;
    @Mock
    LendingRecordRepository lendingRecordRepository;
    @Mock
    BookSearchComponent bookSearchComponent;

    @BeforeEach
    void setup() {
        target = new LibrarianServiceImpl(lendingRecordRepository, bookSearchComponent);
    }

    @Nested
    class lent {
        @Test
        void lent() {
            String isbn = "1234567890123";
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
            String isbn = "1234567890123";
            String userId = "1234567";

            target.receive(isbn, userId);

            LendingRecord expected = new LendingRecord(new Isbn(isbn), userId);
            verify(lendingRecordRepository).receive(expected);
        }
    }

    @DisplayName("本の一覧を取得する")
    @Nested
    class search {
        @DisplayName("検索する")
        @Test
        void test_01() {

            Mockito.when(bookSearchComponent.search(any())).thenReturn(Arrays.asList());
            List<BookStatus> actual = target.search();

            assertThat(actual).hasSize(0);
        }

    }


}