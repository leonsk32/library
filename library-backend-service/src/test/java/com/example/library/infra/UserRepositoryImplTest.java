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
    void test01() {
        jdbcTemplate.execute("insert into USERR(user_id, email) values('1234567', 'aa@bb')");
        String user_id = "1234567";
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
