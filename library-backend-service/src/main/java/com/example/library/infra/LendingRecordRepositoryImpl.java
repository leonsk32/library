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
                + "','"+
                lendingRecord.getUser().getUserId() + "')");
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
        List<Map<String, Object>> resultMap= jdbcTemplate.queryForList(sql);
        ArrayList<LendingRecord> lendingRecords = new ArrayList<>();
        for(Map<String, Object> map : resultMap) {
            String isbn = (String) map.get("isbn");
            String user_id = (String)map.get("user_id");
            lendingRecords.add(new LendingRecord(bookRepository.findById(isbn), userRepository.findById(user_id)));
        }

        return lendingRecords;
    }

    @Override
    public LendingRecord findById(Book book, User user) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from LENDING_RECORD " +
                "where isbn = '" + book.getIsbn() + "' " +
                "and user_id = '" + user.getUserId() + "'");
        String isbn = (String)maps.get(0).get("isbn");
        String userId = (String)maps.get(0).get("user_id");
        return new LendingRecord(bookRepository.findById(isbn), userRepository.findById(userId));
    }
}
