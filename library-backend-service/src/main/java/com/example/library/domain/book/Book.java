package com.example.library.domain.book;

import lombok.Getter;

/**
 * Entity
 */
public class Book {
    private Isbn isbn;

    public Book(String isbn) {
        this.isbn = new Isbn(isbn);
    }

    public String getIsbn() {
        return this.isbn.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Book)) throw new RuntimeException("Book同士を比較して下さい");
        Book otherBook = (Book) other;
        return this.isbn.getIsbn().equals(otherBook.isbn.getIsbn());
    }

    /**
     * ISBNのファクトリでガードしているので
     * パースエラーは発生しない
     */
    @Override
    public int hashCode() {
        return isbn.getIsbn().hashCode();
    }
}
