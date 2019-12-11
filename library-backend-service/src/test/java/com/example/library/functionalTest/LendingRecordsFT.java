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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.RequestEntity.*;

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

    @DisplayName("ハッピーパス" +
            "本を2冊登録" +
            "ユーザを２人登録" +
            "本を借りるx2" +
            "貸し出し帳表示")
    @Test
    void test02() {
        // 本を１冊登録
        URI putUrl1 = URI.create("/v1/books/9781111111111");
        RequestEntity putRequest1 = put(putUrl1).build();
        ResponseEntity<Void> responseBookRegist = restTemplate.exchange(putRequest1, Void.class);
        assertThat(responseBookRegist.getStatusCode()).isEqualTo(OK);
        // 本を登録
        URI putUrl2 = URI.create("/v1/books/9781111111112");
        RequestEntity putRequest2 = put(putUrl2).build();
        ResponseEntity<Void> responseBookRegist2 = restTemplate.exchange(putRequest2, Void.class);
        assertThat(responseBookRegist2.getStatusCode()).isEqualTo(OK);

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

        // ユーザを登録
        URI putUrl22 = URI.create("/v1/users");
        //language=json
        String putRequest22 = "{\n" +
                "  \"userId\": \"1234568\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"ki\",\n" +
                "  \"givenName\": \"na\"\n" +
                "}";
        RequestEntity requestEntity322 = put(putUrl22).contentType(APPLICATION_JSON_UTF8).body(putRequest22);
        ResponseEntity<String> putResponse22 = restTemplate.exchange(requestEntity322, String.class);
        assertThat(putResponse22.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本を借りる
        URI lendingUrl = URI.create("/v1/lendingRecords");
        String lendingRequest = "{\"isbn\":\"9781111111111\",\"userId\":\"1234567\"}";
        RequestEntity lendingRequestEntity = post(lendingUrl).contentType(APPLICATION_JSON_UTF8).body(lendingRequest);
        ResponseEntity<Void> lendingResponse = restTemplate.exchange(lendingRequestEntity, Void.class);
        assertThat(lendingResponse.getStatusCode()).isEqualTo(OK);
        // 本を借りる
        URI lendingUrl2 = URI.create("/v1/lendingRecords");
        String lendingRequest2 = "{\"isbn\":\"9781111111112\",\"userId\":\"1234568\"}";
        RequestEntity lendingRequestEntity2 = post(lendingUrl2).contentType(APPLICATION_JSON_UTF8).body(lendingRequest2);
        ResponseEntity<Void> lendingResponse2 = restTemplate.exchange(lendingRequestEntity2, Void.class);
        assertThat(lendingResponse2.getStatusCode()).isEqualTo(OK);

        // 一覧表示
        URI getLendingRecords = URI.create("/v1/lendingRecords");
        RequestEntity lendingRecordsGetRequest = get(getLendingRecords).build();
        ResponseEntity<LendingRecordsDto> response2 = restTemplate.exchange(lendingRecordsGetRequest, LendingRecordsDto.class);
        assertThat(response2.getStatusCode()).isEqualTo(OK);
        assertThat(response2.getBody().getLendingRecords().size()).isEqualTo(2);

    }

    @DisplayName("本がシステム上ないときに、借りようとしてエラー")
    @Test
    void test03() {
        // ユーザを登録
        URI putUrl22 = URI.create("/v1/users");
        //language=json
        String putRequest22 = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"ki\",\n" +
                "  \"givenName\": \"na\"\n" +
                "}";
        RequestEntity requestEntity322 = put(putUrl22).contentType(APPLICATION_JSON_UTF8).body(putRequest22);
        ResponseEntity<String> putResponse22 = restTemplate.exchange(requestEntity322, String.class);
        assertThat(putResponse22.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本を借りる
        URI lendingUrl = URI.create("/v1/lendingRecords");
        String lendingRequest = "{\"isbn\":\"9781111111111\",\"userId\":\"1234567\"}";
        RequestEntity lendingRequestEntity = post(lendingUrl).contentType(APPLICATION_JSON_UTF8).body(lendingRequest);
        ResponseEntity<String> lendingResponse = restTemplate.exchange(lendingRequestEntity, String.class);
        assertThat(lendingResponse.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(lendingResponse.getBody()).isEqualTo("本が登録されていない");
    }

    @DisplayName("借りてないものを返そうとしてエラー")
    @Test
    void test04() {
        // ユーザを登録
        URI putUrl22 = URI.create("/v1/users");
        //language=json
        String putRequest22 = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"ki\",\n" +
                "  \"givenName\": \"na\"\n" +
                "}";
        RequestEntity requestEntity322 = put(putUrl22).contentType(APPLICATION_JSON_UTF8).body(putRequest22);
        ResponseEntity<String> putResponse22 = restTemplate.exchange(requestEntity322, String.class);
        assertThat(putResponse22.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本を返す
        URI lendingUrlDelete = URI.create("/v1/lendingRecords/9781111111111/1234567");
        RequestEntity lendingRequestEntityDelete = delete(lendingUrlDelete).build();
        ResponseEntity<Void> lendingResponseDelete = restTemplate.exchange(lendingRequestEntityDelete, Void.class);
        assertThat(lendingResponseDelete.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @DisplayName("すべて借りられた本を借りようとしたらエラー")
    @Test
    void test05() {
        // ユーザを登録
        URI putUrl22 = URI.create("/v1/users");
        //language=json
        String putRequest22 = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"ki\",\n" +
                "  \"givenName\": \"na\"\n" +
                "}";
        RequestEntity requestEntity322 = put(putUrl22).contentType(APPLICATION_JSON_UTF8).body(putRequest22);
        ResponseEntity<String> putResponse22 = restTemplate.exchange(requestEntity322, String.class);
        assertThat(putResponse22.getStatusCode()).isEqualTo(HttpStatus.OK);

        // ユーザを登録
        URI putUrl222 = URI.create("/v1/users");
        //language=json
        String putRequest222 = "{\n" +
                "  \"userId\": \"1234568\",\n" +
                "  \"email\": \"aa@bb\",\n" +
                "  \"familyName\": \"nai\",\n" +
                "  \"givenName\": \"nai\"\n" +
                "}";
        RequestEntity requestEntity3222 = put(putUrl222).contentType(APPLICATION_JSON_UTF8).body(putRequest222);
        ResponseEntity<String> putResponse222 = restTemplate.exchange(requestEntity3222, String.class);
        assertThat(putResponse222.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本を１冊登録
        URI putUrl1 = URI.create("/v1/books/9781111111111");
        RequestEntity putRequest1 = put(putUrl1).build();
        ResponseEntity<Void> responseBookRegist = restTemplate.exchange(putRequest1, Void.class);
        assertThat(responseBookRegist.getStatusCode()).isEqualTo(OK);

        // 本を借りる
        URI lendingUrl2 = URI.create("/v1/lendingRecords");
        String lendingRequest2 = "{\"isbn\":\"9781111111111\",\"userId\":\"1234567\"}";
        RequestEntity lendingRequestEntity2 = post(lendingUrl2).contentType(APPLICATION_JSON_UTF8).body(lendingRequest2);
        ResponseEntity<Void> lendingResponse2 = restTemplate.exchange(lendingRequestEntity2, Void.class);
        assertThat(lendingResponse2.getStatusCode()).isEqualTo(OK);

        // 本を借りる
        URI lendingUrl23 = URI.create("/v1/lendingRecords");
        String lendingRequest23 = "{\"isbn\":\"9781111111111\",\"userId\":\"1234568\"}";
        RequestEntity lendingRequestEntity23 = post(lendingUrl23).contentType(APPLICATION_JSON_UTF8).body(lendingRequest23);
        ResponseEntity<Void> lendingResponse23 = restTemplate.exchange(lendingRequestEntity23, Void.class);
        assertThat(lendingResponse23.getStatusCode()).isEqualTo(BAD_REQUEST);

//        // 本を返す
//        URI lendingUrlDelete2 = URI.create("/v1/lendingRecords/9781111111111/1234567");
//        RequestEntity lendingRequestEntityDelete2 = delete(lendingUrlDelete2).build();
//        ResponseEntity<Void> lendingResponseDelete2 = restTemplate.exchange(lendingRequestEntityDelete2, Void.class);
//        assertThat(lendingResponseDelete2.getStatusCode()).isEqualTo(OK);
//
//        // 本を返す
//        URI lendingUrlDelete22 = URI.create("/v1/lendingRecords/9781111111111/1234568");
//        RequestEntity lendingRequestEntityDelete22 = delete(lendingUrlDelete22).build();
//        ResponseEntity<Void> lendingResponseDelete22 = restTemplate.exchange(lendingRequestEntityDelete22, Void.class);
//        assertThat(lendingResponseDelete22.getStatusCode()).isEqualTo(BAD_REQUEST);
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

