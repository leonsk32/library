package com.example.library.infra;

import com.example.library.domain.book.Isbn;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@SpringBootTest
class LendingRecordRepositoryImplTest {
    private LendingRecordRepository target;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from LENDING_RECORD");
        target = new LendingRecordRepositoryImpl(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("Delete from LENDING_RECORD");
    }

    @Test
    void insert() {
        LendingRecord entity = new LendingRecord(new Isbn("9784567890123"), "2");

        target.register(entity);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maps).hasSize(1);
        softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890123");
        softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("2");
        softly.assertAll();
    }

    @DisplayName("receive")
    @Nested
    class receive {
        @Test
        void receive_01() {
            // GIVEN
            LendingRecord entity = new LendingRecord(new Isbn("9784567890123"), "2");

            SoftAssertions softly = new SoftAssertions();
            target.register(entity);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");

            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890123");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("2");

            // WHEN
            target.receive(entity);

            // THEN
            maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
            softly.assertThat(maps).hasSize(0);
            softly.assertAll();
        }
    }

    @DisplayName("find")
    @Nested
    class find {
        @DisplayName("単数の取得")
        @Test
        void test_01() {
            // GIVEN
            LendingRecord entity = new LendingRecord(new Isbn("9784567890123"), "2");

            SoftAssertions softly = new SoftAssertions();
            target.register(entity);

            // THEN
            List<LendingRecord> actual = target.find();
            softly.assertThat(actual).hasSize(1);
            softly.assertThat(actual.get(0)).isEqualTo(entity);
            softly.assertAll();
        }

        @DisplayName("複数の取得")
        @Test
        void test_02() {
            // GIVEN
            LendingRecord entity1 = new LendingRecord(new Isbn("9784567890123"), "2");
            LendingRecord entity2 = new LendingRecord(new Isbn("9784567890124"), "3");
            LendingRecord entity3 = new LendingRecord(new Isbn("9784567890125"), "4");

            SoftAssertions softly = new SoftAssertions();
            target.register(entity1);
            target.register(entity2);
            target.register(entity3);

            // THEN
            List<LendingRecord> actual = target.find();
            softly.assertThat(actual).hasSize(3);
            softly.assertThat(actual.get(0)).isEqualTo(entity1);
            softly.assertThat(actual.get(1)).isEqualTo(entity2);
            softly.assertThat(actual.get(2)).isEqualTo(entity3);
            softly.assertAll();
        }

    }
}