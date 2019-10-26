package com.example.library.restapi.books.dto;

import com.example.library.domain.lending.LendingRecord;
import lombok.Getter;

@Getter
public class BookDto {
    String isbn;
    String userId;
    public BookDto(LendingRecord book) {
        this.isbn = book.getIsbn().getIsbn();
        this.userId = book.getUserId();
    }
}
