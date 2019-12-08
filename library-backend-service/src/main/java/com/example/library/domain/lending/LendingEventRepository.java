package com.example.library.domain.lending;


import com.example.library.infra.dto.LendingEvent;

import java.util.List;

// 実はイベントのリポジトリ説
// なんかイベントの操作しかしてない
// findは実装で明らかにクリエイトしているため
// ファクトリに移譲するたぐいの処理に見える
public interface LendingEventRepository {

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

    List<LendingEvent> find(String isbn, String userId);

}
