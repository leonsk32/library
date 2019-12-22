package com.example.library.infra;

import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingEventRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEvent;
import com.example.library.infra.dto.ReturnEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LendingEventRepositoryImpl implements LendingEventRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void registerForLendingEvent(LendingEvent lendingEvent) {
        jdbcTemplate.execute("insert into lending_event(isbn, user_id, lending_date) values('"
                + lendingEvent.getIsbn()
                + "','"
                + lendingEvent.getUserId()
                + "','" +
                lendingEvent.getLendingDate() + "')");
    }

    @Override
    public void registerForReturnEvent(LendingEvent lendingEvent) {
        jdbcTemplate.execute("insert into return_event(isbn, user_id, return_date) values('"
                + lendingEvent.getIsbn()
                + "','"
                + lendingEvent.getUserId()
                + "','" +
                LocalDateTime.now() + "')");
    }

    @Override
    public List<LendingRecord> findAllForEvent() {

        String lending = "SELECT isbn, user_id, COUNT(isbn) AS count FROM LENDING_EVENT GROUP BY isbn, user_id";
        BeanPropertyRowMapper<LendingEvent> beanMap = new BeanPropertyRowMapper<>(LendingEvent.class);
        List<LendingEvent> lendingResultMapList = jdbcTemplate.query(lending, beanMap);

        String returned = "SELECT isbn, user_id, COUNT(isbn) AS count FROM RETURN_EVENT GROUP BY isbn, user_id";
        BeanPropertyRowMapper<ReturnEvent> map2 = new BeanPropertyRowMapper<>(ReturnEvent.class);

        List<ReturnEvent> returnedResultMapList = jdbcTemplate.query(returned, map2);

        ArrayList<LendingRecord> lendingRecords = new ArrayList<>();

        //isbn,user_idが一致したとき、lendingのほうがcountが多ければlendingRecordsにaddする
        //TODO need refactor
        for (LendingEvent lendingMap : lendingResultMapList) {

            boolean isNoReturnRecord = true;

            for (ReturnEvent returnMap : returnedResultMapList) {

                final RecordMatcher recordMatcher = new RecordMatcher(lendingMap, returnMap);
                if (recordMatcher.isMatch()) {

                    isNoReturnRecord = false;

                    if (recordMatcher.isLending()) {
                        addRecord(lendingRecords, lendingMap);
                    }
                }
            }

            if (isNoReturnRecord) {
                addRecord(lendingRecords, lendingMap);
            }
        }

        return lendingRecords;
    }

    @Override
    public List<LendingEvent> find(String isbn, String userId) {
        String sql = "SELECT isbn, user_id, lending_date FROM LENDING_EVENT " +
                "where isbn = '" + isbn + "' " +
                "AND user_id = '" + userId + "'";
        BeanPropertyRowMapper<LendingEvent> lendingEventBeanPropertyRowMapper = new BeanPropertyRowMapper<>(LendingEvent.class);
        return jdbcTemplate.query(sql, lendingEventBeanPropertyRowMapper);
    }

    private void addRecord(ArrayList<LendingRecord> lendingRecords, LendingEvent lendingMap) {
        lendingRecords.add(new LendingRecord(bookRepository.findById(lendingMap.getIsbn()),
                userRepository.findById(lendingMap.getUserId())));
    }

    @AllArgsConstructor
    private static class RecordMatcher {
        private LendingEvent lendingMap;
        private ReturnEvent returnMap;

        boolean isMatch() {
            return lendingMap.getIsbn().equals(returnMap.getIsbn()) &&
                    lendingMap.getUserId().equals(returnMap.getUserId());
        }

        private boolean isLending() {
            return (long) lendingMap.getCount() - (long) returnMap.getCount() > 0;
        }
    }
}
