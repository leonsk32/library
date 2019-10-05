package com.example.library.biz.bookshelf;

public interface BookshelfRepository {
    Bookshelf get();
    void store(Bookshelf bookshelf);
}
