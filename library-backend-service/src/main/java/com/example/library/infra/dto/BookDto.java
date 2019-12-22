package com.example.library.infra.dto;

import com.example.library.domain.book.Book;
import lombok.Data;

@Data
public class BookDto {
    private String isbn;
    private int amount;

    public Book convert() {
        return new Book(isbn, amount);
    }
}
