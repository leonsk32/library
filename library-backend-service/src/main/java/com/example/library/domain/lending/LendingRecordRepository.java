package com.example.library.domain.lending;


import com.example.library.domain.book.Book;
import com.example.library.domain.user.User;
import com.example.library.infra.dto.LendingEvent;

import java.util.List;

public interface LendingRecordRepository {

    /**
     * 本を借りる
     */
    void registerForLendingEvent(LendingEvent lendingEvent);

    /**
     * 本を返す
     */
    void registerForReturnEvent(LendingEvent lendingEvent);


    List<LendingRecord> findAll();

    /**
     * 借りた本のリストと返した本のリストを突合して、</br>
     * 借りている本の一覧を返却する
     * @return
     */
    List<LendingRecord> findAllForEvent();

    LendingRecord findById(Book book, User user);

}
