package com.example.library.domain.lending;


import java.util.List;

public interface LendingRecordRepository {
    void register(LendingRecord lendingRecord);

    void receive(LendingRecord lendingRecord);

    List<LendingRecord> find();
}
