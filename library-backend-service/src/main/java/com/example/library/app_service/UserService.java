package com.example.library.app_service;

import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
import com.example.library.restapi.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    public User searchById(String userId) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new RuntimeException("ユーザが存在しない。userId = " + userId);
        }
        return user;
    }

    public void register(UserDto userDto) {
        User user = new User(
                userDto.getUserId(),
                userDto.getEmail(),
                userDto.getSimei(),
                userDto.getNamae()
        );

        userRepository.register(user);
    }

    public void delete(String userId) {
        userRepository.delete(userId);
    }
}
