package com.example.library.functionalTest;

import com.example.library.restapi.dto.BooksDto;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookFT {
    @DisplayName("本を廃棄しようとしたが、その本は登録されていなかったのでエラー")
    @Test
    void test01() {
        URI deleteUrl = URI.create("/v1/books/9784567890123");
        RequestEntity deleteRequest = RequestEntity.delete(deleteUrl).build();
        ResponseEntity<String> deleteResponse = restTemplate.exchange(deleteRequest, String.class);
        assertThat(deleteResponse.getBody()).isEqualTo("その本はない");
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("ハッピーパス" +
            "本の登録 本Aを２冊、本Bを１冊登録する, " +
            "その後それを取得する" +
            "本Aと、本Bを削除")
    @Test
    void test02() {
        // 本Aを２冊、本Bを１冊登録
        URI putUrl1 = URI.create("/v1/books/9784567890123");
        URI putUrl2 = URI.create("/v1/books/9784567890124");
        RequestEntity putRequest1 = RequestEntity.put(putUrl1).build();
        RequestEntity putRequest2 = RequestEntity.put(putUrl2).build();
        restTemplate.exchange(putRequest1, String.class);
        restTemplate.exchange(putRequest1, String.class);
        restTemplate.exchange(putRequest2, String.class);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from BOOK");
        assertThat(maps.size()).isEqualTo(2);

        // 本一覧を取得する AとBが存在
        URI getUrl = URI.create("/v1/books");
        RequestEntity getRequest = RequestEntity.get(getUrl).build();
        ResponseEntity<BooksDto> getResponse = restTemplate.exchange(getRequest, BooksDto.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getIsbns()).contains("9784567890123", "9784567890124");

        // 本Aを１冊削除
        URI deleteUrlA = URI.create("/v1/books/9784567890123");
        RequestEntity deleteRequestA = RequestEntity.delete(deleteUrlA).build();
        ResponseEntity<BooksDto> deleteResponseA = restTemplate.exchange(deleteRequestA, BooksDto.class);
        assertThat(deleteResponseA.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 本Bを１冊削除
        URI deleteUrlB = URI.create("/v1/books/9784567890124");
        RequestEntity deleteRequestB = RequestEntity.delete(deleteUrlB).build();
        ResponseEntity<BooksDto> deleteResponseB = restTemplate.exchange(deleteRequestB, BooksDto.class);
        assertThat(deleteResponseB.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Map<String, Object>> map2 = jdbcTemplate.queryForList("select * from BOOK");
        // 本一覧を取得する
        // Aだけ存在する
        URI getUrlA = URI.create("/v1/books");
        RequestEntity getRequestA = RequestEntity.get(getUrlA).build();
        ResponseEntity<BooksDto> getResponseA = restTemplate.exchange(getRequestA, BooksDto.class);
        assertThat(getResponseA.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(getResponseA.getBody()).getIsbns().size()).isEqualTo(1);
        assertThat(Objects.requireNonNull(getResponseA.getBody()).getIsbns().get(0)).isEqualTo("9784567890123");
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

