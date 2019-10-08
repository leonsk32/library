package com.example.library.service;

import com.example.library.biz.BookDeal.BookDealRepository;
import com.example.library.restapi.BookDealDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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




}