package com.example.library.domain.user;

import com.example.library.domain.ranking.RankingList;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(String userId);
    void register(User user);

    void delete(String userId);

    RankingList findLentRanking();
}
