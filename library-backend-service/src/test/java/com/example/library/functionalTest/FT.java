package com.example.library.functionalTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FT{

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @AfterEach
    private void tearDown() {
        jdbcTemplate.execute("DELETE FROM LENDING_RECORD");
        jdbcTemplate.execute("Delete from BOOK");
        jdbcTemplate.execute("Delete from USERR");
    }

    @Nested
    class 貸りる {
        @DisplayName("ユーザーを全検索する")
        @Test
        void test01() {

            jdbcTemplate.execute("insert into USERR(user_id, email) values(1234567, 'aa@bb')");
            jdbcTemplate.execute("insert into USERR(user_id, email, simei, namae) values(1234568, 'aa@bb', 'kiri', 'nai')");

            URI url = URI.create("/v1/users/");

            RequestEntity requestEntity =
                    RequestEntity.get(url).build();

            ResponseEntity<String> response =
                    restTemplate.exchange(requestEntity, String.class);

            System.out.println("response = " + response);
        }
    }

    @Nested
    class 貸りる2 {
        @DisplayName("ユーザーをID検索する")
        @Test
        void test01() {

            URI url = URI.create("/v1/users/1234567");

            RequestEntity requestEntity =
                    RequestEntity.get(url).build();

            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
            System.out.println("exchange = " + exchange);
        }
    }

}
