package com.example.library.functionalTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FT {

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
    class Books {
        @DisplayName("本を廃棄しようとしたが、その本は登録されていなかったのでエラー")
        @Test
        void test01() {
            URI url = URI.create("/v1/books/9784567890123");
            RequestEntity requestEntity =
                    RequestEntity.delete(url).build();
            ResponseEntity<String> response =
                    restTemplate.exchange(requestEntity, String.class);
            assertThat(response.getBody()).isEqualTo("その本はない");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @DisplayName("本の登録系のハッピーパスやらあれこれ")
        @Test
        void test02() {

            // 本Aを２冊、本Bを１冊登録
            URI url1 = URI.create("/v1/books/9784567890123");
            URI url2 = URI.create("/v1/books/9784567890124");
            RequestEntity requestEntity1 = RequestEntity.put(url1).build();
            RequestEntity requestEntity2 = RequestEntity.put(url2).build();
            ResponseEntity<String> response1 = restTemplate.exchange(requestEntity1, String.class);
            ResponseEntity<String> response2 = restTemplate.exchange(requestEntity2, String.class);
            ResponseEntity<String> response3 = restTemplate.exchange(requestEntity1, String.class);

            jdbcTemplate.queryForList("select * from BOOK");
            System.out.println("response3 = " + response3);



        }
    }

    @Nested
    class Users {
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
