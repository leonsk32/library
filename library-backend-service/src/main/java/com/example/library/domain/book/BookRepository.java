package com.example.library.domain.book;

import java.util.List;

public interface BookRepository {
    Book findById(String isbn);

    List<Book> findAll();

    void register(Book book);

    void delete(Book book);

    void save(Book book);
}
