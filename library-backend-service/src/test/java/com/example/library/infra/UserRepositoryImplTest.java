package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.ranking.RankingList;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEvent;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    UserRepository target;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    LendingEventRepositoryImpl lendingRecordRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from BOOK");

        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from BOOK");

        jdbcTemplate.execute("Delete from USERR");
    }

    @DisplayName("取得できるケース")
    @Test
    void test03() {
        String user_id = "1234567";
        User user1 = new User(user_id, "aabb", "a", "a");
        target.register(user1);

        User user = target.findById(user_id);

        assertThat(user).isEqualTo(user1);

        target.delete("1234567");
        User user2 = target.findById(user_id);
        assertThat(user2).isNull();
    }

    @DisplayName("取得できるケース")
    @Test
    void test01() {
        String user_id = "1234567";
        target.register(new User("1234567", "aa@bb", "a", "b"));
        User user = target.findById(user_id);

        assertThat(user).isEqualTo(new User(user_id, "aa@bb"));
    }

    @DisplayName("取得できないケース")
    @Test
    void test02() {
        String user_id = "1234567";
        User user = target.findById(user_id);
        assertThat(user).isNull();
    }

    @Nested
    class ranking {

        @DisplayName("ランキングを1件取得する")
        @Test
        void test_01() {
            // GIVEN
            bookRepository.register(new Book("9784567890978"));
            jdbcTemplate.execute("insert into USERR(user_id, email) values(9784567, 'aa@bb')");


            LendingEvent entity1 = new LendingEvent("9784567890978", "9784567", LocalDateTime.now());
            lendingRecordRepository.registerForLendingEvent(entity1);

            SoftAssertions softly = new SoftAssertions();
            // THEN
            RankingList actual = target.findLentRanking();
            softly.assertThat(actual.getRankingList()).hasSize(1);
            softly.assertThat(actual.getRankingList()).containsExactlyInAnyOrder(
                    new Ranking("9784567", "", 1)
            );
            softly.assertAll();
        }

        @DisplayName("" +
                "・複数件の取得" +
                "・同一内容は集約する")
        @Test
        void test_02() {
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

            lendingRecordRepository.registerForLendingEvent(new LendingEvent(book.getIsbn(), user.getUserId(), LocalDateTime.now()));
            lendingRecordRepository.registerForLendingEvent(new LendingEvent(book1.getIsbn(), user1.getUserId(), LocalDateTime.now()));
            lendingRecordRepository.registerForLendingEvent(new LendingEvent(book2.getIsbn(), user2.getUserId(), LocalDateTime.now()));
            lendingRecordRepository.registerForLendingEvent(new LendingEvent(book1.getIsbn(), user1.getUserId(), LocalDateTime.now()));

            SoftAssertions softly = new SoftAssertions();
            // THEN
            RankingList actual = target.findLentRanking();
            softly.assertThat(actual.getRankingList()).hasSize(3);
            softly.assertThat(actual.getRankingList()).containsExactlyInAnyOrder(
                    new Ranking("9784567", "", 1),
                    new Ranking("9784568", "", 2),
                    new Ranking("9784569", "", 1)
            );
            softly.assertAll();
        }

    }

}
