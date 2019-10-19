package com.example.library.domain.book;

/**
 * Entity
 */
public class Book {
    private Isbn isbn;
    private String title;
    public Book(Isbn isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Book)) throw new RuntimeException("Book同士を比較して下さい");
        Book otherBook = (Book)other;
        return this.isbn.getIsbn().equals(otherBook.isbn.getIsbn());
    }

}
