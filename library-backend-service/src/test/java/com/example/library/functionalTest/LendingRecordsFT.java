package com.example.library.functionalTest;

import com.example.library.restapi.dto.LendingRecordsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.RequestEntity.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LendingRecordsFT {


    @DisplayName("ハッピーパス" +
            "本を一冊登録" +
            "ユーザを登録" +
            "本を借りる" +
            "貸し出し帳表示" +
            "返す" +
            "貸し出し帳票表示０件")
    @Test
    void test01() {
        // 本を１冊登録
        URI putUrl1 = URI.create("/v1/books/9781111111111");
        RequestEntity putRequest1 = put(putUrl1).build();
        ResponseEntity<Void> responseBookRegist = restTemplate.exchange(putRequest1, Void.class);
        assertThat(responseBookRegist.getStatusCode()).isEqualTo(OK);

        // ユーザを登録
        URI putUrl = URI.create("/v1/users");
        //language=json
        String putRequest = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"kiri\",\n" +
                "  \"givenName\": \"nai\"\n" +
                "}";
        RequestEntity requestEntity3 = put(putUrl).contentType(APPLICATION_JSON_UTF8).body(putRequest);
        ResponseEntity<String> putResponse = restTemplate.exchange(requestEntity3, String.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本を借りる
        URI lendingUrl = URI.create("/v1/lendingRecords");
        String lendingRequest = "{\"isbn\":\"9781111111111\",\"userId\":\"1234567\"}";
        RequestEntity lendingRequestEntity = post(lendingUrl).contentType(APPLICATION_JSON_UTF8).body(lendingRequest);
        ResponseEntity<Void> lendingResponse = restTemplate.exchange(lendingRequestEntity, Void.class);
        assertThat(lendingResponse.getStatusCode()).isEqualTo(OK);

        // 一覧表示
        URI getLendingRecords = URI.create("/v1/lendingRecords");
        RequestEntity lendingRecordsGetRequest = get(getLendingRecords).build();
        ResponseEntity<LendingRecordsDto> response2 = restTemplate.exchange(lendingRecordsGetRequest, LendingRecordsDto.class);
        assertThat(response2.getStatusCode()).isEqualTo(OK);
        assertThat(response2.getBody().getLendingRecords().size()).isEqualTo(1);
        assertThat(response2.getBody().getLendingRecords().get(0).getIsbn()).isEqualTo("9781111111111");
        assertThat(response2.getBody().getLendingRecords().get(0).getUserId()).isEqualTo("1234567");

        // 本を返す
        URI lendingUrlDelete = URI.create("/v1/lendingRecords/9781111111111/1234567");
        RequestEntity lendingRequestEntityDelete = delete(lendingUrlDelete).build();
        ResponseEntity<Void> lendingResponseDelete = restTemplate.exchange(lendingRequestEntityDelete, Void.class);
        assertThat(lendingResponseDelete.getStatusCode()).isEqualTo(OK);

        // 一覧表示０件
        URI getLendingRecordsZero = URI.create("/v1/lendingRecords");
        RequestEntity lendingRecordsGetRequestZero = get(getLendingRecordsZero).build();
        ResponseEntity<LendingRecordsDto> response2Zero = restTemplate.exchange(lendingRecordsGetRequestZero, LendingRecordsDto.class);
        assertThat(response2Zero.getStatusCode()).isEqualTo(OK);
        assertThat(response2Zero.getBody().getLendingRecords().size()).isEqualTo(0);
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

