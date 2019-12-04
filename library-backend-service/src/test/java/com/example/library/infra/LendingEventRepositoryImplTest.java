package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEvent;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class LendingEventRepositoryImplTest {

    @Autowired
    LendingEventRepositoryImpl target;

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
//        jdbcTemplate.execute("Delete from LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from RETURN_EVENT");
//        jdbcTemplate.execute("Delete from LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @DisplayName("insert")
    @Nested
    class Insert {

        @Test
        void insertForLendingEvent() {
            // Arrange
            jdbcTemplate.execute("insert into BOOK (isbn) values(9784567890978)");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");
            Book book = new Book("9784567890978");
            User user = new User("9784567", "aa@bb");
            LendingEvent lendingEvent = new LendingEvent(book.getIsbn(), user.getUserId(), LocalDateTime.now());

            // Act
            target.registerForLendingEvent(lendingEvent);

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
            Book book = new Book("9784567890978");
            User user = new User("9784567", "aa@bb");
            LendingEvent lendingEvent = new LendingEvent(book.getIsbn(), user.getUserId(), LocalDateTime.now());

            // Act
            target.registerForReturnEvent(lendingEvent);

            // Assert
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM RETURN_EVENT");
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo("9784567890978");
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo("9784567");
            softly.assertAll();
        }

    }

    @Disabled
    @DisplayName("find")
    @Nested
    class find {

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
            Book book = new Book("9784567890978");
            User user = new User("9784567", "aa@BB");
            Book book1 = new Book("9784567890124");
            User user1 = new User("9784568", "ab@BB");
            Book book2 = new Book("9784567890125");
            User user2 = new User("9784569", "ac@BB");

//            target.registerForLendingEvent(entity1);
//            target.registerForLendingEvent(entity2);
//            target.registerForLendingEvent(entity2);
//            target.registerForLendingEvent(entity3);
//            target.registerForLendingEvent(entity3);
//            target.registerForReturnEvent(entity2);
//            target.registerForReturnEvent(entity2);
//            target.registerForReturnEvent(entity3);
//
//            WHEN
//            List<LendingRecord> actual = target.findAllForEvent();
//
//             THEN
//            assertThat(actual.size()).isEqualTo(2);
//            assertThat(actual).containsExactlyInAnyOrder(
//                entity1,
//                entity3
//            );
        }
    }
}