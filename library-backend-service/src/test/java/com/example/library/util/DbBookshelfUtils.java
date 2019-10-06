package com.example.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DbBookshelfUtils {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getAmountFor(String isbn10) {
        int amount = -1;
        List<Map<String, Object>> queryResult = jdbcTemplate.queryForList("select * from BOOK");
        for (Map book : queryResult) {
            if (book.get("ISBN10").equals(isbn10)) {
                amount = (Integer) book.get("AMOUNT");
            }
        }
        if (amount == -1) throw new RuntimeException("そのISBNの本がないぞ");
        return amount;
    }

    public void deleteAll(String tableName) {
        jdbcTemplate.execute("delete from " + tableName);
    }

    public void insertBook(String isbn10, int amount) {
        jdbcTemplate.execute("insert into book(isbn10, amount) values('" + isbn10 + "'," + amount + ")");
    }

}
