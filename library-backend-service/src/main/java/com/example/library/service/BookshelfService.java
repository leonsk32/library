package com.example.library.service;

import com.example.library.biz.bookshelf.Bookshelf;
import com.example.library.biz.bookshelf.BookshelfRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookshelfService {
    private BookshelfRepository bookshelfRepository;
    public BookshelfService(BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    public void borrow(String isbn10) {
        Bookshelf bookshelf = bookshelfRepository.get();
        bookshelf.takeOut(isbn10);
        bookshelfRepository.store(bookshelf);
    }
}
