package com.example.library.infra;

import com.example.library.biz.domain.LendingRecord;

public interface LendingRecordRepository {
    void insert(LendingRecord lendingRecord);
}
