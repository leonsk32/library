package com.example.library.service;

import com.example.library.biz.BookDeal.BookDealRepository;
import com.example.library.restapi.BookDealDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class BorrowServiceTest {

    private BorrowService service;

    private BookDealRepository bookDealRepository;

    @BeforeEach
    void setUp() {
        bookDealRepository = mock(BookDealRepository.class);
        service = new BorrowService(bookDealRepository);
    }

    @DisplayName("success")
    @Test
    void test01() {
        BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("1234567890")
                .personId("1234567")
                .build();
        service.borrow(bookDealDto);
        doNothing().when(bookDealRepository).save(any());
        verify(bookDealRepository).save(any());
    }

    @DisplayName("isbn10が１０桁でない")
    @Test
    void test02() {
        BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("12345678")
                .personId("1234567")
                .build();
        assertThatThrownBy(() -> service.borrow(bookDealDto))
                .isInstanceOf(RuntimeException.class);

    }

    @DisplayName("personIdが７桁ではない")
    @Test
    void test03() {
        BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("1234567890")
                .personId("12345")
                .build();
        assertThatThrownBy(() -> service.borrow(bookDealDto))
                .isInstanceOf(RuntimeException.class);
    }

    // TODO 実装する　仕様パターンがはまるかも
    @Disabled
    @DisplayName("登録されていないpersonIdなのでエラー")
    @Test
    void test04() {
        BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("2234567890")
                .personId("12345")
                .build();
        assertThatThrownBy(() -> service.borrow(bookDealDto))
                .isInstanceOf(RuntimeException.class);
    }
    @Disabled
    @DisplayName("登録されていない本なのでエラー")
    @Test
    void test05() {
        BookDealDto bookDealDto = BookDealDto.builder()
                .isbn10("2234567890")
                .personId("12345")
                .build();
        assertThatThrownBy(() -> service.borrow(bookDealDto))
                .isInstanceOf(RuntimeException.class);
    }
}