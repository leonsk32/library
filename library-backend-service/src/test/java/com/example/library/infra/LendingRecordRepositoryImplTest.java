package com.example.library.infra;

import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional // トランザクションはAPService管理したほうがよいらしい ←Test内部で毎回DBを初期化したいため。つけないと、registerとreceiveで一意制約違反で失敗する。
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
        LendingRecord entity = new LendingRecord(new Isbn("1", ""), "2");

        target.register(entity);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maps).hasSize(1);
        softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("1");
        softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("2");
        softly.assertAll();
    }

    @DisplayName("receive")
    @Nested
    class receive {
        @Test
        void receive_01() {
            // GIVEN
            LendingRecord entity = new LendingRecord(new Isbn("1", ""), "2");

            SoftAssertions softly = new SoftAssertions();
            target.register(entity);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");

            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("1");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("2");

            // WHEN
            target.receive(entity);

            // THEN
            maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
            softly.assertThat(maps).hasSize(0);
            softly.assertAll();
        }
    }
}