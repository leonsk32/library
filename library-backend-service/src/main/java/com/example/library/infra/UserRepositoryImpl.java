package com.example.library.infra;

import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.ranking.RankingList;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEvent;
import com.example.library.infra.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERR";
        BeanPropertyRowMapper<UserDto> rowMapper = new BeanPropertyRowMapper<>(UserDto.class);
        List<UserDto> resultMap = jdbcTemplate.query(sql, rowMapper);
        if (resultMap.isEmpty()) return emptyList();

        List<User> result = new ArrayList<>();
        for (UserDto map : resultMap) {
            result.add(map.convert());
        }
        return result;
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT * FROM USERR where user_id = ?";

        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        pscFactory.addParameter(new SqlParameter(Types.VARCHAR));
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(Arrays.asList(userId));
        BeanPropertyRowMapper<UserDto> rowMapper = new BeanPropertyRowMapper<>(UserDto.class);

        List<UserDto> resultMap = jdbcTemplate.query(psc, rowMapper);
        if (resultMap.isEmpty()) return null;
        return resultMap.get(0).convert();
    }

    @Override
    public void register(User user) {
        String sql = "insert into userr(user_id, email, family_name, given_name) values (?,?,?,?)";

        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        pscFactory.addParameter(new SqlParameter(Types.VARCHAR));
        pscFactory.addParameter(new SqlParameter(Types.VARCHAR));
        pscFactory.addParameter(new SqlParameter(Types.VARCHAR));
        pscFactory.addParameter(new SqlParameter(Types.VARCHAR));
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(
                        user.getUserId(),
                        user.getEmail(),
                        user.getFamilyName(),
                        user.getGivenName()
                )
        );

        jdbcTemplate.update(psc);
    }

    @Override
    public void delete(String userId) {
        String sql = "delete from userr where user_id = '" + userId + "';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public RankingList findLentRanking() {
        String sql = "SELECT * FROM LENDING_EVENT";
        BeanPropertyRowMapper<LendingEvent> rowMapper = new BeanPropertyRowMapper<>(LendingEvent.class);
        List<LendingEvent> result = jdbcTemplate.query(sql, rowMapper);

        RankingList rankingList = new RankingList();
        for (LendingEvent dto : result) {
            User user = findById(dto.getUserId());
            Ranking ranking = new Ranking(dto.getUserId(), user.getFullName());
            rankingList.add(ranking);
        }
        return rankingList;
    }
}
