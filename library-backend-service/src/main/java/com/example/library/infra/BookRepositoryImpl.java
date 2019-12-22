package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.infra.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Book findById(String isbn) {
        String sql = "SELECT * FROM book where isbn = '" + isbn + "'";
        BeanPropertyRowMapper<BookDto> beanMap = new BeanPropertyRowMapper<>(BookDto.class);

        List<BookDto> resultMap = jdbcTemplate.query(sql, beanMap);
        if (resultMap.isEmpty()) {
            return null;
        }
        return resultMap.get(0).convert();
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        BeanPropertyRowMapper<BookDto> beanMap = new BeanPropertyRowMapper<>(BookDto.class);
        List<BookDto> resultMap = jdbcTemplate.query(sql, beanMap);

        if (resultMap.isEmpty()){
            return emptyList();
        }
        for (BookDto bookDto : resultMap) {
            books.add(bookDto.convert());
        }
        return books;
    }

    @Override
    public void register(Book book) {
        String sql = "insert into book (isbn, amount) values (" +
                book.getIsbn() + "," + book.getAmount() + ");";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void delete(Book book) {
        String sql = "delete from book where isbn = '" + book.getIsbn() + "';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void save(Book book) {
        String sql = "update  book set amount=" + book.getAmount() + " where isbn = '" + book.getIsbn() + "';";
        jdbcTemplate.execute(sql);
    }

}
