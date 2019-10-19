package com.example.library.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LendingRecordTest {
    private LendingRecord target;

    @Mock
    LendingRecordRepository lendingRecordRepository;

    @BeforeEach
    void setup() {
        target = new LendingRecord(lendingRecordRepository);
    }

    @Test
    void test_01(){
        target.lent();
        verify(lendingRecordRepository).insert(target);
    }
}