package com.example.library.infra;

import com.example.library.domain.Isbn;
import com.example.library.domain.LendingRecord;
import com.example.library.domain.LendingRecordRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//@Transactional トランザクションはAPService管理したほうがよいらしい
@SpringBootTest
class LendingRecordRepositoryImplTest {
    private LendingRecordRepository target;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        target = new LendingRecordRepositoryImpl(jdbcTemplate);
    }

    @Test
    void insert() {
        LendingRecord entity = new LendingRecord(new Isbn("1",""), "2");

        target.regist(entity);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maps).hasSize(1);
        softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("1");
        softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("2");
        softly.assertAll();
    }
}