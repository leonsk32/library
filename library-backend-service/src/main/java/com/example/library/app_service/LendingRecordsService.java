package com.example.library.app_service;

import com.example.library.domain.lending.LendingRecord;

import java.util.List;

public interface LendingRecordsService {
    List<LendingRecord> search(String... options);

    /**
     * 借りた本のリストと返した本のリストを突合して、</br>
     * 借りている本の一覧を返却する
     * @return
     */
    List<LendingRecord> searchForEvent(String... options);

    void borrow(String isbn, String userId);

    void returnn(String isbn, String userId);

}
