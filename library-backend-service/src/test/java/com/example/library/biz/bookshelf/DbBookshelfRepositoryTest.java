package com.example.library.biz.bookshelf;

import com.example.library.infra.DbBookshelfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbBookshelfRepositoryTest {
    @Autowired
    DbBookshelfRepository repository;
    @Test
    void name() {
        Bookshelf bookshelf = repository.get();
    }
}