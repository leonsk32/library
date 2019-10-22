package com.example.library.infra;

import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LendingRecordRepositoryImpl implements LendingRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void register(LendingRecord lendingRecord) {
        jdbcTemplate.execute("insert into lending_record(isbn, user_id) values(" + lendingRecord.getIsbn().getIsbn() + ","+ lendingRecord.getUserId() + ")");
    }

    @Override
    public void receive(LendingRecord lendingRecord) {
        String sql = "DELETE FROM LENDING_RECORD WHERE isbn = '" + lendingRecord.getIsbn().getIsbn() + "' AND USER_ID='" + lendingRecord.getUserId() + "';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<LendingRecord> find() {
        String sql = "SELECT * FROM LENDING_RECORD";
        RowMapper<LendingRecord> mapper = new BeanPropertyRowMapper<LendingRecord>(LendingRecord.class);
        return jdbcTemplate.query(sql, mapper);
    }
}
