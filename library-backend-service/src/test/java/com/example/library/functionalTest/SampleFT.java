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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleFT {

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

    @Disabled("うごきはしないけど、テストケース鶴くときに参考にする")
    @Test
    void sample() {

        // GETサンプル
        URI getUrl = URI.create("/v1/lendingRecords");
        RequestEntity getRequest = RequestEntity.get(getUrl).build();
        ResponseEntity<String> getResponse = restTemplate.exchange(getRequest, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // PUTサンプル
        URI putUrl = URI.create("/v1/books/9781111111111");
        RequestEntity requestEntity3 = RequestEntity.put(putUrl).build();
        ResponseEntity<String> putResponse = restTemplate.exchange(requestEntity3, String.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Postサンプル
        URI postUrl = URI.create("/v1/lendingRecords");
        // language=json
        String postRequest = "{\"isbn\":\"9781111111111\",\"userId\":\"1234568\"}";
        RequestEntity requestEntity = RequestEntity.post(postUrl).contentType(MediaType.APPLICATION_JSON_UTF8).body(postRequest);
        ResponseEntity<String> postResponse = restTemplate.exchange(requestEntity, String.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Deleteサンプル
        URI deleteUrl = URI.create("/v1/books/" + "9781111111111");
        RequestEntity deleteRequest = RequestEntity.delete(deleteUrl).build();
        ResponseEntity<String> deleteResponse = restTemplate.exchange(deleteRequest, String.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

