package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Book findById(String isbn) {
        String sql = "SELECT * FROM book where isbn = '" + isbn + "'";
        List<Map<String, Object>> resultMap= jdbcTemplate.queryForList(sql);
        if(resultMap.size() == 0) return null;
        String title =  (String)resultMap.get(0).get("title");
        Book book = new Book(isbn, title);
        return book;
    }
}
