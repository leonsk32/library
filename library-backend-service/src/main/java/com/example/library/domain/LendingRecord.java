package com.example.library.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class LendingRecord {
    private final LendingRecordRepository lendingRecordRepository;
    private Isbn isbn;
    private String userId;

    public LendingRecord(LendingRecordRepository repository) {
        lendingRecordRepository = repository;
    }

    public LendingRecord create(Isbn isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
        return this;
    }

    public void lent() {
        lendingRecordRepository.insert(this);
    }
}
