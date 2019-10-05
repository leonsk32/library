package com.example.library.biz.bookshelf;

import java.util.ArrayList;

public class InMemoryBookshelfRepository implements BookshelfRepository {
    @Override
    public Bookshelf get() {
        return new Bookshelf(new ArrayList<>());
    }
}
