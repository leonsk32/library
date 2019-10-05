package com.example.library.infra;

import com.example.library.biz.bookshelf.Book;
import com.example.library.biz.bookshelf.Bookshelf;
import com.example.library.biz.bookshelf.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO ORMapperとかつかうべきか？
@Repository
public class DbBookshelfRepository implements BookshelfRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Bookshelf get() {
        List<Book> books = new ArrayList<>();
        List<Map<String, Object>> bookList = jdbcTemplate.queryForList("SELECT * FROM BOOK");
        for(Map book : bookList) {
            books.add(new Book((String)book.get("ISBN10"), (Integer)book.get("AMOUNT")));
        }

        return new Bookshelf(books);
    }

    @Override
    public void store(Bookshelf bookshelf) {
        jdbcTemplate.execute("delete from BOOK");
        for(Book book : bookshelf.getBookList()) {
            // TODO sql injection　ありうる
            jdbcTemplate.execute("insert into book(isbn10, amount) values(" + book.getIsbn10() + ","+ book.getAmount() + ")");
        }
    }
}
