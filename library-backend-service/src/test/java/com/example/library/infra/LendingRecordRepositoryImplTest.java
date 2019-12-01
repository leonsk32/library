package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class LendingRecordRepositoryImplTest {

    @Autowired
    LendingRecordRepositoryImpl target;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from RETURN_EVENT");
        jdbcTemplate.execute("Delete from LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from RETURN_EVENT");
        jdbcTemplate.execute("Delete from LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @DisplayName("insert")
    @Nested
    class Insert {

        @Test
        void insert() {
            // Arrange
            jdbcTemplate.execute("insert into BOOK (isbn) values(9784567890978)");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            LendingRecord entity = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@bb"));

            // Act
            target.register(entity);

            // Assert
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890978");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("9784567");
            softly.assertAll();
        }

        @Test
        void insertForLendingEvent() {
            // Arrange
            jdbcTemplate.execute("insert into BOOK (isbn) values(9784567890978)");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            LendingRecord entity = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@bb"));

            // Act
            target.registerForLendingEvent(entity);

            // Assert
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_EVENT");
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890978");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("9784567");
            softly.assertAll();
        }

        @Test
        void insertForRentalEvent() {
            // Arrange
            jdbcTemplate.execute("insert into BOOK (isbn) values(9784567890978)");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            LendingRecord entity = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@bb"));

            // Act
            target.registerForReturnEvent(entity);

            // Assert
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM RETURN_EVENT");
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890978");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("9784567");
            softly.assertAll();
        }

    }

    @DisplayName("delete")
    @Nested
    class dlete {
        @Test
        void dlete01() {
            // GIVEN
            bookRepository.register(new Book("9784567890978"));
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            LendingRecord entity = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@bb"));

            SoftAssertions softly = new SoftAssertions();
            target.register(entity);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");

            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890978");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("9784567");

            // WHEN
            target.delete(entity);

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
        void findById_01() {
            // GIVEN
            bookRepository.register(new Book("9784567890978"));
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            Book book = new Book("9784567890978");
            User user = new User("9784567", "aa@bb");
            LendingRecord entity = new LendingRecord(book, user);

            // WHEN
            target.register(entity);

            // THEN
            LendingRecord actual = target.findById(book, user);
            assertThat(actual).isEqualTo(entity);
        }


        @DisplayName("複数の取得")
        @Test
        void findAllForEvent() {
            // GIVEN
            bookRepository.register(new Book("9784567890978"));
            bookRepository.register(new Book("9784567890124"));
            bookRepository.register(new Book("9784567890125"));
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784568, 'ab@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784569, 'ac@bb')");
            LendingRecord entity1 = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@BB"));
            LendingRecord entity2 = new LendingRecord(new Book("9784567890124"), new User("9784568", "ab@BB"));
            LendingRecord entity3 = new LendingRecord(new Book("9784567890125"), new User("9784569", "ac@BB"));

            SoftAssertions softly = new SoftAssertions();
            target.register(entity1);
            target.register(entity2);
            target.register(entity3);

//             THEN
            List<LendingRecord> actual = target.findAll();
            softly.assertThat(actual).hasSize(3);
            softly.assertThat(actual).containsExactlyInAnyOrder(
              entity1,
              entity2,
              entity3
            );
            softly.assertAll();
        }

        @DisplayName("５件中返却が３件されているため、2件が表示されること")
        @Test
        void findAll_02() {
            // GIVEN
            bookRepository.register(new Book("9784567890978"));
            bookRepository.register(new Book("9784567890124"));
            bookRepository.register(new Book("9784567890125"));
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784568, 'ab@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784569, 'ac@bb')");
            LendingRecord entity1 = new LendingRecord(new Book("9784567890978"), new User("9784567", "aa@BB"));
            LendingRecord entity2 = new LendingRecord(new Book("9784567890124"), new User("9784568", "ab@BB"));
            LendingRecord entity3 = new LendingRecord(new Book("9784567890125"), new User("9784569", "ac@BB"));

            target.registerForLendingEvent(entity1);
            target.registerForLendingEvent(entity2);
            target.registerForLendingEvent(entity2);
            target.registerForLendingEvent(entity3);
            target.registerForLendingEvent(entity3);
            target.registerForReturnEvent(entity2);
            target.registerForReturnEvent(entity2);
            target.registerForReturnEvent(entity3);

            //WHEN
            List<LendingRecord> actual = target.findAllForEvent();

            // THEN
            assertThat(actual.size()).isEqualTo(2);
            assertThat(actual).containsExactlyInAnyOrder(
                entity1,
                entity3
            );
        }
    }
}