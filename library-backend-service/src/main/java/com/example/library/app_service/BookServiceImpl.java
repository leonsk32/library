package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<Book> searchAll() {
        return repository.findAll();
    }

    @Override
    public void register(String isbn) {
        repository.register(new Book(isbn));
    }

    @Override
    public void waste(String isbn) {
        Book book = repository.findById(isbn);
        repository.delete(book);
    }
}
