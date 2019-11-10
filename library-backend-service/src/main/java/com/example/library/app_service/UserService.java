package com.example.library.app_service;

import com.example.library.domain.user.User;
import com.example.library.domain.user.UserRepository;
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
}
