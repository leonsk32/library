package com.example.library.app_service;

public interface LibrarianService {
    void lent(String isbn, String userId);

    void receive(String isbn, String userId);
}
