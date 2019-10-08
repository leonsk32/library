package com.example.library.biz.BookDeal;

import com.example.library.restapi.BookDealDto;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookDealTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("isbn10が10桁でない")
    @Test
    void test01() {
        assertThatThrownBy(() -> new BookDeal("12345", "1234567"))
                .isInstanceOf(RuntimeException.class);

    }

    @DisplayName("personIdが７桁ではない")
    @Test
    void test02() {
        assertThatThrownBy(() -> new BookDeal("1234567890", "123"))
                .isInstanceOf(RuntimeException.class);
    }

    // TODO 実装する　仕様パターンがはまるかも
    @Disabled
    @DisplayName("登録されていないpersonIdなのでエラー")
    @Test
    void test04() {
    }

    @Disabled
    @DisplayName("登録されていない本なのでエラー")
    @Test
    void test05() {
    }

    @Disabled
    @DisplayName("借りている本をまた借りようとしたためエラー")
    @Test
    void test06() {
    }
}