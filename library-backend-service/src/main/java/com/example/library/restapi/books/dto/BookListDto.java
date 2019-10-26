package com.example.library.restapi.books.dto;

import com.example.library.domain.lending.LendingRecord;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookListDto {
    List<BookDto> books;
    public BookListDto(List<LendingRecord> search) {
        books = new ArrayList<>();
        for (LendingRecord book : search){
            this.books.add(new BookDto(book));
        }
    }
}
