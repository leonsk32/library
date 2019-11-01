package com.example.library.restapi.books.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookListDto {
    List<String> isbns;
}
