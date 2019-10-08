package com.example.library.service;

import com.example.library.biz.BookDeal.BookDeal;
import com.example.library.biz.BookDeal.BookDealRepository;
import com.example.library.restapi.BookDealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowService {
    private final BookDealRepository bookDealRepository;
    public void borrow(BookDealDto bookDealDto) {
        BookDeal bookDeal = new BookDeal(bookDealDto.getIsbn10(), bookDealDto.getPersonId());
        bookDealRepository.save(bookDeal);
    }

}
