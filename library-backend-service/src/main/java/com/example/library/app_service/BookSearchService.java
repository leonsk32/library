package com.example.library.app_service;

import com.example.library.domain.BookStatus;

import java.util.List;

public interface BookSearchService {
    List<BookStatus> search(String... options);
}
