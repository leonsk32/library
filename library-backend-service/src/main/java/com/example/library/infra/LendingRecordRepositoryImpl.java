package com.example.library.infra;

import com.example.library.domain.book.Book;
import com.example.library.domain.book.BookRepository;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.lending.LendingRecordRepository;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
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
        String returned = "SELECT isbn, user_id, COUNT(isbn) AS count FROM RETURN_EVENT GROUP BY isbn, user_id";

        List<Map<String, Object>> lendingResultMapList = jdbcTemplate.queryForList(lending);
        List<Map<String, Object>> returnedResultMapList = jdbcTemplate.queryForList(returned);
        ArrayList<LendingRecord> lendingRecords = new ArrayList<>();

        //isbn,user_idが一致したとき、lendingのほうがcountが多ければlendingRecordsにaddする
        for (Map<String, Object> lendingMap : lendingResultMapList) {

            String lendingIsbn = (String) lendingMap.get("isbn");
            String lendingUserId = (String) lendingMap.get("user_id");
            long lendingCount = (long) lendingMap.get("count");

            boolean isNoReturnRecord = true;

            for (Map<String, Object> returnMap : returnedResultMapList) {


                String returnIsbn = (String) returnMap.get("isbn");
                String returnUserId = (String) returnMap.get("user_id");
                long returnCount = (long) returnMap.get("count");

                if (lendingIsbn.equals(returnIsbn) &&
                    lendingUserId.equals(returnUserId)) {

                    isNoReturnRecord = false;

                    if (lendingCount - returnCount <= 0) {
                        continue;
                    }

                    lendingRecords.add(new LendingRecord(bookRepository.findById(lendingIsbn),
                        userRepository.findById(lendingUserId)));

                }
            }

            if (isNoReturnRecord) {
                lendingRecords.add(new LendingRecord(bookRepository.findById(lendingIsbn),
                    userRepository.findById(lendingUserId)));
            }

        }

        return lendingRecords;
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

}
