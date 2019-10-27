package com.example.library.domain;

import com.example.library.app_service.LendingRecordsServiceImpl;
import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import com.example.library.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LendingRecordsServiceImplTest {
    private LendingRecordsServiceImpl target;
    @Mock
    LendingRecordRepository lendingRecordRepository;


    @BeforeEach
    void setup() {
        target = new LendingRecordsServiceImpl(lendingRecordRepository);
    }

    @Test
    void search() {

        List<LendingRecord> lendingRecords = Arrays.asList(
                new LendingRecord(new Book("9784567890123", "titleA"), new User("1234567", "aa@bb")),
                new LendingRecord(new Book("9784567890124", "titleB"), new User("1234568", "aa@bb"))
        );

        when(lendingRecordRepository.findAll()).thenReturn(lendingRecords);
        List<LendingRecord> actual = target.search();

        List<LendingRecord> expected = lendingRecords;
        assertThat(actual).isEqualTo(expected);
    }
}