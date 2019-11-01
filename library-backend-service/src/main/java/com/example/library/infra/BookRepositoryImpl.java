package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        Book book = new Book(isbn);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        List<Map<String, Object>> resultMap= jdbcTemplate.queryForList(sql);
        if(resultMap.size() == 0) return null;
        for (Map<String, Object> stringObjectMap : resultMap) {
            String isbn = (String) stringObjectMap.get("isbn");
            books.add(new Book(isbn));
        }
        return books;
    }

    @Override
    public void register(Book book) {

    }

    @Override
    public void delete(Book book) {

    }
}
