package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEventDto;
import com.example.library.infra.dto.ReturnEventDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LendingRecordRepositoryImpl implements LendingRecordRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void register(LendingRecord lendingRecord) {
        jdbcTemplate.execute("insert into lending_record(isbn, user_id) values('"
                + lendingRecord.getBook().getIsbn()
                + "','" +
                lendingRecord.getUser().getUserId() + "')");
    }

    @Override
    public void registerForLendingEvent(LendingRecord lendingRecord) {
        jdbcTemplate.execute("insert into lending_event(isbn, user_id, lending_date) values('"
                + lendingRecord.getBook().getIsbn()
                + "','"
                + lendingRecord.getUser().getUserId()
                + "','" +
                LocalDateTime.now() + "')");
    }

    @Override
    public void registerForReturnEvent(LendingRecord lendingRecord) {
        jdbcTemplate.execute("insert into return_event(isbn, user_id, return_date) values('"
                + lendingRecord.getBook().getIsbn()
                + "','"
                + lendingRecord.getUser().getUserId()
                + "','" +
                LocalDateTime.now() + "')");
    }

    @Override
    public void delete(LendingRecord lendingRecord) {
        String sql = "DELETE FROM LENDING_RECORD WHERE isbn = '"
                + lendingRecord.getBook().getIsbn()
                + "' AND USER_ID='"
                + lendingRecord.getUser().getUserId() + "';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<LendingRecord> findAll() {
        String sql = "SELECT * FROM LENDING_RECORD";
        List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql);
        ArrayList<LendingRecord> lendingRecords = new ArrayList<>();
        for (Map<String, Object> map : resultMap) {
            String isbn = (String) map.get("isbn");
            String user_id = (String) map.get("user_id");
            lendingRecords.add(new LendingRecord(bookRepository.findById(isbn), userRepository.findById(user_id)));
        }

        return lendingRecords;
    }

    @Override
    public List<LendingRecord> findAllForEvent() {

        String lending = "SELECT isbn, user_id, COUNT(isbn) AS count FROM LENDING_EVENT GROUP BY isbn, user_id";
        BeanPropertyRowMapper<LendingEventDto> beanMap = new BeanPropertyRowMapper<LendingEventDto>(LendingEventDto.class);
        List<LendingEventDto> lendingResultMapList = jdbcTemplate.query(lending, beanMap);

        String returned = "SELECT isbn, user_id, COUNT(isbn) AS count FROM RETURN_EVENT GROUP BY isbn, user_id";
        BeanPropertyRowMapper<ReturnEventDto> map2 = new BeanPropertyRowMapper<ReturnEventDto>(ReturnEventDto.class);

        List<ReturnEventDto> returnedResultMapList = jdbcTemplate.query(returned, map2);

        ArrayList<LendingRecord> lendingRecords = new ArrayList<>();

        //isbn,user_idが一致したとき、lendingのほうがcountが多ければlendingRecordsにaddする
        //TODO need refactor
        for (LendingEventDto lendingMap : lendingResultMapList) {

            boolean isNoReturnRecord = true;

            for (ReturnEventDto returnMap : returnedResultMapList) {

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

    private void addRecord(ArrayList<LendingRecord> lendingRecords, LendingEventDto lendingMap) {
        lendingRecords.add(new LendingRecord(bookRepository.findById(lendingMap.getIsbn()),
                userRepository.findById(lendingMap.getUserId())));
    }

    @Override
    public LendingRecord findById(Book book, User user) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from LENDING_RECORD " +
                "where isbn = '" + book.getIsbn() + "' " +
                "and user_id = '" + user.getUserId() + "'");
        String isbn = (String) maps.get(0).get("isbn");
        String userId = (String) maps.get(0).get("user_id");
        return new LendingRecord(bookRepository.findById(isbn), userRepository.findById(userId));
    }

    @AllArgsConstructor
    private static class RecordMatcher {
        private LendingEventDto lendingMap;
        private ReturnEventDto returnMap;

        boolean isMatch() {
            return lendingMap.getIsbn().equals(returnMap.getIsbn()) &&
                    lendingMap.getUserId().equals(returnMap.getUserId());
        }

        private boolean isLending() {
            return (long) lendingMap.getCount() - (long) returnMap.getCount() > 0;
        }
    }
}
