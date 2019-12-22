package com.example.library.infra.dto;

import com.example.library.domain.user.User;
import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String email;
    private String familyName;
    private String givenName;

    public User convert(){
        return new User(userId, email, familyName, givenName);
    }
}
