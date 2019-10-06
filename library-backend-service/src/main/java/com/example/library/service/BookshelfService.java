package com.example.library.service;

import com.example.library.biz.bookshelf.Bookshelf;
import com.example.library.biz.bookshelf.BookshelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookshelfService {
    private final BookshelfRepository bookshelfRepository;

    public void borrow(String isbn10) {
        Bookshelf bookshelf = bookshelfRepository.get();
        bookshelf.takeOut(isbn10);
        bookshelfRepository.store(bookshelf);
    }
}
