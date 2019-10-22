package com.example.library.restapi.books.dto;

import com.example.library.domain.BookStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookListDto {
    List<BookDto> books;
    public BookListDto(List<BookStatus> search) {
        books = new ArrayList<>();
        for (BookStatus book : search){
            this.books.add(new BookDto(book));
        }
    }
}
