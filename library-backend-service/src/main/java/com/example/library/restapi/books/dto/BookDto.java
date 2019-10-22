package com.example.library.restapi.books.dto;

import com.example.library.domain.BookStatus;
import lombok.Getter;

@Getter
public class BookDto {
    String isbn;
    String userId;
    public BookDto(BookStatus book) {
        this.isbn = book.getLendingRecord().getIsbn().getIsbn();
        this.userId = book.getLendingRecord().getUserId();
    }
}
