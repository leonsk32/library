package com.example.library.functionalTest;

import com.example.library.restapi.books.dto.BookListDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FT_BooksTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    class 検索 {
        @DisplayName("本の一覧を取得する")
        @Test
        void test_01() throws URISyntaxException {
            URI url = URI.create("/v1/books");

            RequestEntity requestEntity = RequestEntity.get(url).build();

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            response.getBody().toString();

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            // TODO: FTのjsonを比較するのってどうやればいい？
            softly.assertThat(response.getBody().toString()).isEqualTo("{books=[]}");
            softly.assertAll();
        }
    }

}
