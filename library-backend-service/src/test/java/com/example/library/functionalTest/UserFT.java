package com.example.library.functionalTest;

import com.example.library.restapi.dto.UserDto;
import com.example.library.restapi.dto.UsersDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserFT {

    @DisplayName("ハッピーパス" +
            "登録" +
            "全件検索" +
            "ID検索" +
            "削除" +
            "検索")
    @Test
    void test01() {
        // ユーザを登録
        URI putUrl = URI.create("/v1/users");
        //language=json
        String putRequest = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"kiri\",\n" +
                "  \"givenName\": \"nai\"\n" +
                "}";
        RequestEntity requestEntity3 = RequestEntity.put(putUrl).contentType(APPLICATION_JSON_UTF8).body(putRequest);
        ResponseEntity<String> putResponse = restTemplate.exchange(requestEntity3, String.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(OK);

        // ユーザを全件検索する
        URI getUrlAll = URI.create("/v1/users/");
        RequestEntity getRequestAll = RequestEntity.get(getUrlAll).build();
        ResponseEntity<UsersDto> getAllResponse = restTemplate.exchange(getRequestAll, UsersDto.class);
        assertThat(getAllResponse.getBody().getUsers().size()).isEqualTo(1);
        assertThat(getAllResponse.getStatusCode()).isEqualTo(OK);

        // ユーザをID検索する
        URI getUrl = URI.create("/v1/users/1234567");
        RequestEntity getRequest = RequestEntity.get(getUrl).build();
        ResponseEntity<UserDto> getResponse = restTemplate.exchange(getRequest, UserDto.class);
        assertThat(getResponse.getBody().getUserId()).isEqualTo("1234567");
        assertThat(getResponse.getStatusCode()).isEqualTo(OK);

        // ユーザを削除
        URI deleteUrlB = URI.create("/v1/users/1234567");
        RequestEntity deleteRequestB = RequestEntity.delete(deleteUrlB).build();
        ResponseEntity<Void> deleteResponseB = restTemplate.exchange(deleteRequestB, Void.class);

        // ユーザをID検索する
        URI getUrlZero = URI.create("/v1/users/1234567");
        RequestEntity getRequestZero = RequestEntity.get(getUrlZero).build();
        ResponseEntity<String> getResponseZero = restTemplate.exchange(getRequestZero, String.class);
    }

    @DisplayName("いないユーザを削除しようとしてエラー")
    @Test
    void test02() {
        // ユーザを削除
        URI deleteUrlB = URI.create("/v1/users/1234567");
        RequestEntity deleteRequestB = RequestEntity.delete(deleteUrlB).build();
        ResponseEntity<Void> deleteResponseB = restTemplate.exchange(deleteRequestB, Void.class);
        assertThat(deleteResponseB.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

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
}

