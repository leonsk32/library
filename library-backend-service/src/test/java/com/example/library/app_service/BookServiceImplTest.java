package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    private BookServiceImpl target;
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        target = new BookServiceImpl(bookRepository);
    }

    @Test
    void test01() {
        when(bookRepository.findAll()).thenReturn(emptyList());
        target.searchAll();
        verify(bookRepository).findAll();
    }

    @Disabled
    @Test
    void test02() {
        final String isbn = "9781234567890";
        final Book book = new Book(isbn);
        doNothing().when(bookRepository).register(book);
        target.register(isbn);
        verify(bookRepository).register(book);
    }

    @Test
    void test03() {
        final String isbn = "9781234567890";
        final Book book = new Book(isbn);
        book.decliment();
        when(bookRepository.findById(isbn)).thenReturn(book);
        doNothing().when(bookRepository).delete(book);
        target.waste(isbn);
        verify(bookRepository).delete(book);
    }
}