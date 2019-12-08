package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingEventRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.ReturnEventRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.ReturnEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LendingRecordsServiceImplTest {
    private LendingRecordsServiceImpl target;
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    LendingEventRepository lendingEventRepository;
    @Mock
    ReturnEventRepository returnEventRepository;

    @BeforeEach
    void setup() {
        target = new LendingRecordsServiceImpl(lendingEventRepository, bookRepository, userRepository, returnEventRepository);
    }

    @Test
    void searchForEvent() {

        List<LendingRecord> lendingRecords = Arrays.asList(
                new LendingRecord(new Book("9784567890123"), new User("1234567", "aa@bb")),
                new LendingRecord(new Book("9784567890124"), new User("1234568", "aa@bb"))
        );

        when(lendingEventRepository.findAllForEvent()).thenReturn(lendingRecords);
        List<LendingRecord> actual = target.searchForEvent();

        List<LendingRecord> expected = lendingRecords;
        assertThat(actual).isEqualTo(expected);
    }

    @Disabled
    @Test
    void borrow() {
        // arrange
        final String isbn = "9781234567890";
        final String userId = "1234567";
        Book book = new Book(isbn);
        User user = new User(userId, "aa@bb");

        // act
        when(bookRepository.findById(isbn)).thenReturn(book);
        when(userRepository.findById(userId)).thenReturn(user);
        doNothing().when(lendingEventRepository).registerForReturnEvent(any());
        target.borrow(isbn, userId);

        // assert
        verify(bookRepository).findById("9781234567890");
        verify(userRepository).findById("1234567");
        verify(lendingEventRepository).registerForReturnEvent(any());
    }

    @DisplayName("借りてないものを返そうとしてエラー")
    @Test
    void test01() {
        // arrange
        final String isbn = "9781234567890";
        final String userId = "1234567";
        Book book = new Book(isbn);
        User user = new User(userId);

        // act
        when(bookRepository.findById(isbn)).thenReturn(book);
        when(userRepository.findById(userId)).thenReturn(user);
        when(lendingEventRepository.find(isbn, userId)).thenReturn(List.of());
        when(returnEventRepository.find(isbn, userId)).thenReturn(List.of(new ReturnEvent("9781234567890", "1234567", LocalDateTime.now(), 1)));
        assertThatThrownBy(()->target.returnn(isbn, userId)).isInstanceOf(RuntimeException.class);

        verify(lendingEventRepository, never()).registerForReturnEvent(any());

    }
}