package com.example.library.domain.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsbnTest {
    private static final String EVANS_DDD_ISBN10 = "4798121967";
    private static final String EVANS_DDD_ISBN13 = "9784798121963";
    private static final String PRACTICE_DDD_ISBN10 = "479813161X";
    private static final String PRACTICE_DDD_ISBN13 = "9784798131610";

    @DisplayName("10桁、13桁以外が入ってきた場合、RuntimeException")
    @Test
    void test_01(){
        assertThrows(RuntimeException.class,
                () ->new Isbn("12345"));
    }

    @DisplayName("[エリックエヴァンス本]10桁が入ってきた場合、13桁のISBNに変換する")
    @Test
    void test_02_01() {
        assertThat(EVANS_DDD_ISBN13).isEqualTo(new Isbn(EVANS_DDD_ISBN10).getIsbn());
    }

    @DisplayName("[実践DDD本]10桁が入ってきた場合、13桁のISBNに変換する")
    @Test
    void test_02_02() {
        assertThat(PRACTICE_DDD_ISBN13).isEqualTo(new Isbn(PRACTICE_DDD_ISBN10).getIsbn());
    }

    @DisplayName("13桁が入ってきた場合、そのままISBNに設定する")
    @Test
    void test_03() {
        assertThat(EVANS_DDD_ISBN13).isEqualTo(new Isbn(EVANS_DDD_ISBN13).getIsbn());
    }
}