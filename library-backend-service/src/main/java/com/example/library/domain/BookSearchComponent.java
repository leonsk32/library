package com.example.library.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSearchComponent {
    public List<BookStatus> search(String ... options) {
        return Arrays.asList();
    }
}
