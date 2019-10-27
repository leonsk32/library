package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 貸借サービス
 */
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LendingRecordRepository lendingRecordRepository;

    @Override
    public void lent(String isbn, String userId) {
        Book book = bookRepository.findById(isbn);
        if(book == null) {
            throw new RuntimeException("借りようとした本が登録されていない");
        }
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new RuntimeException("借りようとしたユーザが登録されていない");
        }
        LendingRecord record = new LendingRecord(book, user);
        lendingRecordRepository.register(record);
    }

    @Override
    public void receive(String isbn, String userId) {
        Book book = bookRepository.findById(isbn);
        User user = userRepository.findById(userId);
        LendingRecord record = lendingRecordRepository.findById(book, user);
        lendingRecordRepository.delete(record);
    }
}
