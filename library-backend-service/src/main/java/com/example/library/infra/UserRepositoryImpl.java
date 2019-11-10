package com.example.library.infra;

import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERR";
        List<Map<String, Object>> resultMap= jdbcTemplate.queryForList(sql);
        if(resultMap.size() == 0) return emptyList();

        List<User> result = new ArrayList<>();
        for(Map<String, Object> map : resultMap) {
            String userId =  (String)map.get("user_id");
            String email =  (String)map.get("email");
            String simei =  (String)map.get("simei");
            String namae =  (String)map.get("namae");
            User user = new User(userId, email, simei, namae);
            result.add(user);
        }
        return result;
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT * FROM USERR where user_id = '" + userId + "'";
        List<Map<String, Object>> resultMap= jdbcTemplate.queryForList(sql);
        if(resultMap.size() == 0) return null;
        String email =  (String)resultMap.get(0).get("email");
        User user = new User(userId, email);
        return user;
    }
}
