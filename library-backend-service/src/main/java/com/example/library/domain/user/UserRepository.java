package com.example.library.domain.user;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(String userId);
}
