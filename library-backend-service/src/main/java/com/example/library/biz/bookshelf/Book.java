package com.example.library.biz.bookshelf;

/**
 * Entity
 * 本を表現
 * 本棚からしかアクセスできないので、メソッドにはパブリックはない
 * <p>
 * TODO ISBNには１３桁バージョンもあるが、一意性をどう担保するか？？？
 */
class Book {

    private String isbn10;
    private int amount;

    Book(String isbn) {
        this.isbn10 = isbn;
        this.amount = 1;
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
