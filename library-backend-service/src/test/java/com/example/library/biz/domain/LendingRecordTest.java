package com.example.library.biz.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LendingRecordTest {
    private LendingRecord target;

    @BeforeEach
    void setup() {
        target = new LendingRecord();

    }
}