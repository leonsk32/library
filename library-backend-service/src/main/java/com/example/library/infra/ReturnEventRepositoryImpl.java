package com.example.library.infra;

import com.example.library.domain.lending.ReturnEventRepository;
import com.example.library.infra.dto.ReturnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReturnEventRepositoryImpl implements ReturnEventRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ReturnEvent> findAll() {
        return null;
    }

    @Override
    public List<ReturnEvent> find(String isbn, String userId) {
        String sql = "SELECT isbn, user_id, return_date FROM return_event " +
                "where isbn = '" + isbn + "' " +
                "AND user_id = '" + userId + "'";
        BeanPropertyRowMapper<ReturnEvent> lendingEventBeanPropertyRowMapper = new BeanPropertyRowMapper<>(ReturnEvent.class);
        return jdbcTemplate.query(sql, lendingEventBeanPropertyRowMapper);
    }
}
