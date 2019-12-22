package com.example.library.domain.book;

import lombok.Getter;

/**
 * Entity
 */
public class Book {
    private Isbn isbn;
    @Getter
    private int amount;

    /**
     * ファクトリ用のコンストラクタ
     */
    public Book(String isbn) {
        this.isbn = new Isbn(isbn);
        this.amount = 1;
    }

    /**
     * 再構成用のコンストラクタ
     */
    public Book(String isbn, int amount) {
        this.isbn = new Isbn(isbn);
        this.amount = amount;
    }

    public String getIsbn() {
        return this.isbn.toString();
    }


    public void add(){
        amount++;
    }

    public void decliment(){
        amount--;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Book)) throw new RuntimeException("Book同士を比較して下さい");
        Book otherBook = (Book) other;
        return this.isbn.getCode().equals(otherBook.isbn.getCode());
    }

    /**
     * ISBNのファクトリでガードしているので
     * パースエラーは発生しない
     */
    @Override
    public int hashCode() {
        return isbn.getCode().hashCode();
    }
}
