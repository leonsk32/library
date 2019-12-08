package com.example.library.app_service;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingEventRepository;
import com.example.library.domain.lending.ReturnEventRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEvent;
import com.example.library.infra.dto.ReturnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LendingRecordsServiceImpl implements LendingRecordsService {
    private final LendingEventRepository lendingEventRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReturnEventRepository returnEventRepository;

    @Override
    public List<LendingRecord> searchForEvent(String... options) {
        return lendingEventRepository.findAllForEvent();
    }

    @Override
    public void borrow(String isbn, String userId) {
        Book book = bookRepository.findById(isbn);
        User user = userRepository.findById(userId);

        if (user == null) throw new RuntimeException("ユーザが登録されていない");
        if (book == null) throw new RuntimeException("本が登録されていない");

        LendingEvent lendingEvent = new LendingEvent(book.getIsbn(), user.getUserId(), LocalDateTime.now());
        lendingEventRepository.registerForLendingEvent(lendingEvent);
    }

    @Override
    public void returnn(String isbn, String userId) {
        Book book = bookRepository.findById(isbn);
        User user = userRepository.findById(userId);

        if (user == null) throw new RuntimeException("ユーザが登録されていない");
        if (book == null) throw new RuntimeException("本が登録されていない");
        if (!isBorrow(user, isbn)) throw new RuntimeException("かりてないのに返そうとしています");

        LendingEvent lendingEvent = new LendingEvent(book.getIsbn(), user.getUserId(), LocalDateTime.now());
        lendingEventRepository.registerForReturnEvent(lendingEvent);
    }


    private boolean isBorrow(User user, String isbn) {
        List<LendingEvent> lendingEvents = lendingEventRepository.find(isbn, user.getUserId());
        List<ReturnEvent> returnEvents = returnEventRepository.find(isbn, user.getUserId());

        return lendingEvents.size()
                > returnEvents.size();
    }
}
