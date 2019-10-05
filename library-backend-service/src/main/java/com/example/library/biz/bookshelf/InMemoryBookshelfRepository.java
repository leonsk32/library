package com.example.library.biz.bookshelf;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InMemoryBookshelfRepository implements BookshelfRepository {

    private Bookshelf bookshelf;
    private List<Book> bookList = Arrays.asList(

            new Book("1111111111"),
            new Book("1211111111"),
            new Book("1311111111")
    );

    @Override
    public Bookshelf get() {

        return new Bookshelf(bookList);
    }

    @Override
    public void store(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

}
