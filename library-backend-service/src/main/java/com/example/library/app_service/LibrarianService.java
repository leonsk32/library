package com.example.library.app_service;

import com.example.library.domain.BookStatus;

import java.util.List;

public interface LibrarianService {
    void lent(String isbn, String userId);

    void receive(String isbn, String userId);

    List<BookStatus> search(String ... options);
}
