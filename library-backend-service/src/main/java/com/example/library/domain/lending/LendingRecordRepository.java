package com.example.library.domain.lending;


import com.example.library.domain.book.Book;
import com.example.library.domain.user.User;

import java.util.List;

public interface LendingRecordRepository {

    void register(LendingRecord lendingRecord);

    /**
     * 本を借りる
     * @param lendingRecord
     */
    void registerForLendingEvent(LendingRecord lendingRecord);

    /**
     * 本を返す
     * @param lendingRecord
     */
    void registerForReturnEvent(LendingRecord lendingRecord);

    void delete(LendingRecord lendingRecord);

    List<LendingRecord> findAll();

    /**
     * 借りた本のリストと返した本のリストを突合して、</br>
     * 借りている本の一覧を返却する
     * @return
     */
    List<LendingRecord> findAllForEvent();

    LendingRecord findById(Book book, User user);

}
