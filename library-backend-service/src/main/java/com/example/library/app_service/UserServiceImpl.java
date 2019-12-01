package com.example.library.app_service;

import com.example.library.domain.ranking.Ranking;
import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.restapi.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    @Override
    public User searchById(String userId) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new RuntimeException("ユーザが存在しない。userId = " + userId);
        }
        return user;
    }

    @Override
    public void register(UserDto userDto) {
        User byId = userRepository.findById(userDto.getUserId());
        if(byId != null) {
            return;
        }
        User user = new User(
                userDto.getUserId(),
                userDto.getEmail(),
                userDto.getSimei(),
                userDto.getNamae()
        );

        userRepository.register(user);
    }

    @Override
    public void delete(String userId) {
        userRepository.delete(userId);
    }

    @Override
    public List<Ranking> searchLentRanking() {
        return null;
    }
}
