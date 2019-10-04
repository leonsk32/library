package com.example.library.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository {
    private final JdbcTemplate jdbcTemplate;

    public int get() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT 1 AS DUMMY");
        return (int)list.get(0).get("DUMMY");
    }

}
