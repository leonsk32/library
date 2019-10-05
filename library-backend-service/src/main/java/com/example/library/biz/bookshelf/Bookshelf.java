package com.example.library.biz.bookshelf;

import java.util.List;

/**
 * Aggregate
 * <p>
 * 本棚を表現
 */
public class Bookshelf {
    private List<Book> bookList;

    Bookshelf(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void putBook(String isbn10) {
        // 本がない場合、あたらしく本棚にいれる、あったら数を増やす
        if (existBook(isbn10)) {
            bookList.add(new Book(isbn10));
        } else {
            this.get(isbn10).plus();
        }
    }

    private boolean existBook(String isbn10) {
        return bookList.stream().noneMatch(book -> book.equals(isbn10));
    }

    public Book takeOut(String isbn10) {
        Book book = get(isbn10);
        if (book.amount() == 1) {
            bookList.remove(book);
        } else {
            book.minus();
        }
        return book;
    }

    public Book get(String isbn10) {
        return bookList.stream()
                .filter(book -> book.equals(isbn10))
                .findFirst().orElseThrow(() -> new RuntimeException("そのISBNの本がねえ" + isbn10));
    }

}
