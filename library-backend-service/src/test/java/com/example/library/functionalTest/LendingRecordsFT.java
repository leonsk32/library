package com.example.library.functionalTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LendingRecordsFT {

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

    @DisplayName("借りて一覧表示")
    @Test
    void test01() {
        // 本を登録
        URI url3 = URI.create("/v1/books/9781111111111");
        RequestEntity requestEntity3 = RequestEntity.put(url3).build();
        ResponseEntity<String> response3 = restTemplate.exchange(requestEntity3, String.class);

        jdbcTemplate.execute("insert into USERR(user_id, email, family_name, given_name) values(1234568, 'aa@bb', 'kiri', 'nai')");

        // 本を借りる
        URI url1 = URI.create("/v1/lendingRecords");
//            RequestEntity requestEntity1 = RequestEntity.post(url1).build();
//            ResponseEntity<String> response1 = restTemplate.exchange(requestEntity1, String.class);
        // language=json
        String request = "{\"isbn\":\"9781111111111\",\"userId\":\"1234568\"}";
        RequestEntity requestEntity =
                RequestEntity.post(url1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(request);
        ResponseEntity<String> response =
                restTemplate.exchange(requestEntity, String.class);
        System.out.println("response = " + response);


        // 一覧表示

        URI url2 = URI.create("/v1/lendingRecords");
        RequestEntity requestEntity2 =
                RequestEntity.get(url2).build();
        ResponseEntity<String> response2 = restTemplate.exchange(requestEntity2, String.class);
        System.out.println("response2 = " + response2);
    }
}

