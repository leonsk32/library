package com.example.library.infra;

import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    UserRepository target;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    void tearDown() {
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
}
