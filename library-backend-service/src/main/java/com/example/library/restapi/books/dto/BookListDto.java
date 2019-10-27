package com.example.library.restapi.books.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookListDto {
    List<BookDto> books;
}
