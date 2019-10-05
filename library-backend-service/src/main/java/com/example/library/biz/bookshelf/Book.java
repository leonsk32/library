package com.example.library.biz.bookshelf;

import lombok.Getter;

/**
 * Entity
 * 本を表現
 * 本棚からしかアクセスできないので、メソッドにはパブリックはない
 * <p>
 * TODO ISBNには１３桁バージョンもあるが、一意性をどう担保するか？？？
 */
public class Book {

    @Getter
    private String isbn10;
    @Getter
    private int amount;

    Book(String isbn10) {
        if(!(isbn10.length() == 10)) {
            new RuntimeException("isbn（１０桁）の桁数がおかしいですわ");
        }
        this.isbn10 = isbn10;
        this.amount = 1;
    }

    /**
     * 再構成用のコンストラクタ
     */
    public Book(String isbn10, int amount) {
        this.isbn10 = isbn10;
        this.amount = amount;
    }


    int amount() {
        return this.amount;
    }

    void plus() {
        this.amount++;
    }

    void minus() {
        if(this.amount == 0) {
            throw new RuntimeException("その本の０冊しかない:isbn10" + isbn10);
        }
        this.amount--;
    }

    // FIXME もっと良い名前にしないとわかりにくい
    boolean equals(String isbn10) {
        return this.isbn10.equals(isbn10);
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) throw new RuntimeException("本ではない！！");

        Book other = (Book) object;
        return this.isbn10.equals(other.isbn10);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(isbn10);
    }
}
