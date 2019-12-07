package com.example.library.functionalTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserFT {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from RETURN_EVENT");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    private void tearDown() {
        jdbcTemplate.execute("Delete from LENDING_EVENT");
        jdbcTemplate.execute("Delete from RETURN_EVENT");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @Nested
    class Users {
        @DisplayName("ユーザーを全検索する")
        @Test
        void test01() {

            jdbcTemplate.execute("insert into USERR(user_id, email) values(1234567, 'aa@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email, family_name, given_name) values(1234568, 'aa@bb', 'kiri', 'nai')");

            URI url = URI.create("/v1/users/");

            RequestEntity requestEntity =
                    RequestEntity.get(url).build();

            ResponseEntity<String> response =
                    restTemplate.exchange(requestEntity, String.class);

            System.out.println("response = " + response);
        }

        @DisplayName("ユーザーをID検索する")
        @Test
        void test02() {

            URI url = URI.create("/v1/users/1234567");

            RequestEntity requestEntity =
                    RequestEntity.get(url).build();

            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
            System.out.println("exchange = " + exchange);
        }
    }
}

