package com.example.library.domain;

import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class LendingRecordTest {
    private LendingRecord target;


    @BeforeEach
    void setup() {
//        target = new LendingRecord();
    }

    @Disabled
    @DisplayName("同一性のテスト")
    @Test
    void test_01() {
        // arrange
        Book book = new Book("9874567890", "tile1");
        User user = new User("1234567", "aa@bb");
        LendingRecord lendingRecord1 = new LendingRecord(book, user);
        LendingRecord lendingRecord2 = new LendingRecord(book, user);

        // act and assert
        assertThat(lendingRecord1).isEqualTo(lendingRecord2);

    }
}