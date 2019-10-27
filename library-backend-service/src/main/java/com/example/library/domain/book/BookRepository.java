package com.example.library.domain.book;

public interface BookRepository {
    Book findById(String isbn);
}
