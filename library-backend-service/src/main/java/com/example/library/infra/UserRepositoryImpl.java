package com.example.library.infra;

import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.ranking.RankingList;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.infra.dto.LendingEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERR";
        List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql);
        if (resultMap.size() == 0) return emptyList();

        List<User> result = new ArrayList<>();
        for (Map<String, Object> map : resultMap) {
            String userId = (String) map.get("user_id");
            String email = (String) map.get("email");
            String simei = (String) map.get("simei");
            String namae = (String) map.get("namae");
            User user = new User(userId, email, simei, namae);
            result.add(user);
        }
        return result;
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT * FROM USERR where user_id = '" + userId + "'";
        List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql);
        if (resultMap.size() == 0) return null;
        String email = (String) resultMap.get(0).get("email");
        String simei = (String) resultMap.get(0).get("simei");
        String namae= (String) resultMap.get(0).get("namae");
        User user = new User(userId, email, simei, namae);
        return user;
    }

    @Override
    public void register(User user) {
        String sql = "insert into userr(user_id, email, family_name, given_name) values ('" +
                user.getUserId() + "','" +
                user.getEmail() + "','" +
                user.getSimei() + "','" +
                user.getNamae() + "')";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void delete(String userId) {
        String sql = "delete from userr where user_id = '" + userId + "';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public RankingList findLentRanking() {
        String sql = "SELECT * FROM LENDING_EVENT";
        BeanPropertyRowMapper<LendingEventDto> rowMapper = new BeanPropertyRowMapper<LendingEventDto>(LendingEventDto.class);
        List<LendingEventDto> result = jdbcTemplate.query(sql, rowMapper);

        RankingList rankingList = new RankingList();
        for (LendingEventDto dto : result) {
            User user = findById(dto.getUserId());
            Ranking ranking = new Ranking(dto.getUserId(), user.getFullName());
            rankingList.add(ranking);
        }
        return rankingList;
    }
}
