package com.example.library.functionalTest;

import org.assertj.core.api.SoftAssertions;
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
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FT_BooksIsbnActionsTest {

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
        @DisplayName("１冊の本を借りる")
        @Test
        void test01() {

            jdbcTemplate.execute("insert into BOOK (isbn, title) values(9781111111111, 'titleA')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(1234567, 'aa@bb')");

            final String isbn = "9781111111111";
            String userId = "1234567";

            //language=JSON
            String request = "{" +
                    "\"type\":\"borrow\"," +
                    "\"userId\":\"1234567\"" +
                    "}";

            URI url = URI.create("/v1/books/" + isbn + "/actions");

            RequestEntity requestEntity =
                    RequestEntity.post(url)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(request);

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");

            softly.assertThat(maps).hasSize(1);
            softly.assertThat(maps.get(0).get("ISBN")).isEqualTo(isbn);
            softly.assertThat(maps.get(0).get("USER_ID")).isEqualTo(userId);
            softly.assertAll();
        }
    }

    @Nested
    class 返す {
        @DisplayName("１冊の本を返す")
        @Test
        void test01() {
            jdbcTemplate.execute("insert into BOOK (isbn, title) values(9781111111111, 'titleA')");
            jdbcTemplate.execute("insert into USERR(user_id, email) values(1234567, 'aa@bb')");

            final String isbn = "9781111111111";
            String userId = "1234567";

            jdbcTemplate.execute("INSERT INTO LENDING_RECORD values('" + isbn + "', '" + userId + "')");

            //language=JSON
            String request = "{" +
                    "\"type\":\"return\"," +
                    "\"userId\":\"1234567\"" +
                    "}";

            URI url = URI.create("/v1/books/" + isbn + "/actions");

            RequestEntity requestEntity =
                    RequestEntity.post(url)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(request);

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM LENDING_RECORD");
            softly.assertThat(maps).hasSize(0);
            softly.assertAll();
        }
    }


    @Nested
    class Validator {
        @DisplayName("パラメータのtypeがNotNull制約に引っかかる")
        @Test
        void test01() throws URISyntaxException {

            final String isbn = "1111111111111";

            //language=JSON
            String request = "{}";

            URI url = URI.create("/v1/books/" + isbn + "/actions");

            RequestEntity requestEntity =
                    RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).body(request);

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
