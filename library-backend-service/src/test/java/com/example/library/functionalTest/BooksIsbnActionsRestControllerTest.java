package com.example.library.functionalTest;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BooksIsbnActionsRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("１冊の本を借りる")
    @Test
    void test01() {

        final String isbn = "1111111111";

        String url = "/v1/books/" + isbn + "/actions";
        ResponseEntity<Object> response = restTemplate.postForEntity(url, null, null);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertAll();

    }
}
