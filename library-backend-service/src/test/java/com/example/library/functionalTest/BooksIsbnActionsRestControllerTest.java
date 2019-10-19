package com.example.library.functionalTest;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BooksIsbnActionsRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    class 貸りる {
        @DisplayName("１冊の本を借りる")
        @Test
        void test01() throws URISyntaxException {

            final String isbn = "1111111111";

            //language=JSON
            String request = "{" +
                    "\"type\":\"borrow\"," +
                    "\"userId\":\"1234567\"" +
                    "}";

            URI url = URI.create("/v1/books/" + isbn + "/actions");

            RequestEntity requestEntity =
                    RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).body(request);

            ResponseEntity<Object> response =
                    restTemplate.exchange(requestEntity, Object.class);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            softly.assertAll();
        }
    }


    @Nested
    class Validator {
        @DisplayName("パラメータのtypeがNotNull制約に引っかかる")
        @Test
        void test01() throws URISyntaxException {

            final String isbn = "1111111111";

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
