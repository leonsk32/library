package com.example.library.infra;

import com.example.library.biz.domain.LendingRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LendingRecordRepositoryImpl implements LendingRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(LendingRecord lendingRecord) {
        jdbcTemplate.execute("insert into lending_record(isbn, user_id) values(" + lendingRecord.getIsbn().getIsbn() + ","+ lendingRecord.getUserId() + ")");
    }
}
