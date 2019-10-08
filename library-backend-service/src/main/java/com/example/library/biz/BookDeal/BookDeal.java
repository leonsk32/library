package com.example.library.biz.BookDeal;

public class BookDeal {
    // TODO IDはDBで生成するので、再構成でしかつかない遅延生成
    private Integer id;
    private final String isbn10;
    private final String personId;

    /**
     * 新規作成用のファクトリ
     * @param isbn10
     * @param personId
     */
    public BookDeal(String isbn10, String personId) {
        if (isbn10.length() != 10 ||
                personId.length() != 7) {
            throw new RuntimeException();
        }
        this.isbn10 = isbn10;
        this.personId = personId;
    }
}
