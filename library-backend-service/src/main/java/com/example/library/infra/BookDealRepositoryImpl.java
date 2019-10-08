package com.example.library.infra;

import com.example.library.biz.BookDeal.BookDeal;
import com.example.library.biz.BookDeal.BookDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookDealRepositoryImpl implements BookDealRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(BookDeal bookDeal) {
        jdbcTemplate.execute("insert into book_deal(isbn10, person_id)values(1234567890, 1234567)");
    }
}
