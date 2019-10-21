package com.example.library.functionalTest;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
class FT_BooksIsbnTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    class 登録 {
        @DisplayName("１冊の本を登録する")
        @Test
        void test01() throws URISyntaxException {
            final String isbn = "1111111111111";
            URI url = URI.create("/v1/books/" + isbn);

            RequestEntity requestEntity = RequestEntity.put(url).build();

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
            softly.assertAll();
        }
    }

    @Nested
    class 削除 {
        @DisplayName("１冊の本を削除する")
        @Test
        void test01() throws URISyntaxException {
            final String isbn = "1111111111111";
            URI url = URI.create("/v1/books/" + isbn);

            RequestEntity requestEntity = RequestEntity.delete(url).build();

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
            softly.assertAll();
        }
    }
}
