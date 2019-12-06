package com.example.library.app_service;

import com.example.library.domain.ranking.RankingList;
import com.example.library.domain.user.User;
import com.example.library.restapi.dto.UserDto;

import java.util.List;

public interface UserService {
    List<User> searchAll();

    User searchById(String userId);

    void register(UserDto userDto);

    void delete(String userId);

    RankingList searchLentRanking();
}
