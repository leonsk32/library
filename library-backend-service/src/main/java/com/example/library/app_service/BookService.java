package com.example.library.app_service;

import com.example.library.domain.book.Book;

import java.util.List;

public interface BookService {
    List<Book> searchAll();

    void register(String isbn);

    void waste(String isbn);
}
