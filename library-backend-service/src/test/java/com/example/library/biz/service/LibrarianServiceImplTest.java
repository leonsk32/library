package com.example.library.biz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {
    LibrarianService target;

    @BeforeEach
    void setup(){
        target = new LibrarianServiceImpl();
    }

    @Test
    void lent() {
        target.lent(null, null);
    }
}