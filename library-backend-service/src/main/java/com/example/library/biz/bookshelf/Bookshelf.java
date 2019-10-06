package com.example.library.biz.bookshelf;

import lombok.Getter;

import java.util.List;

/**
 * Aggregate
 * これは集約ではなくてサービスに近いものかもしれない
 * IDがないがライフサイクルの管理は必要？？？
 * <p>
 * 本棚を表現
 */
public class Bookshelf {
    @Getter
    private List<Book> bookList;

    public Bookshelf(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * 新規に本を登録する
     */
    public void registBooks(String isbn10, int amount) {
        for (int y = 0; y < amount; y++) {
            registBook(isbn10);
        }
    }

    /**
     * 新規の本を１冊登録する
     */
    public void registBook(String isbn10) {
        if (existBook(isbn10)) {
            bookList.add(new Book(isbn10));
        } else {
            this.get(isbn10).plus();
        }
    }

    /**
     * 借りていた本を本棚に戻す
     */
    public void returnBook(String isbn10) {
        this.get(isbn10).plus();
    }


    /**
     * 本を１冊取り出す
     */
    public void takeOut(String isbn10) {
        Book book = get(isbn10);
        book.minus();
    }

    Book get(String isbn10) {
        return bookList.stream()
                .filter(book -> book.equals(isbn10))
                .findFirst().orElseThrow(() -> new RuntimeException("No book with isbn10 : isbn10 = " + isbn10));
    }

    public int getAmount(String isbn10) {
        return get(isbn10).amount();
    }

    private boolean existBook(String isbn10) {
        return bookList.stream().noneMatch(book -> book.equals(isbn10));
    }
}
