package com.example.library.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class BusinessDateTimeResolverTests {
    private BusinessDateTimeResolver target;

    @BeforeEach
    void setup() {
        target = new BusinessDateTimeResolverImpl();
    }

    @Disabled("テストの方法が分からない")
    @Test
    void test_01() {
        LocalDateTime localDateTime = target.getLocalDateTime();
        System.out.println(localDateTime.toString());
    }

}
