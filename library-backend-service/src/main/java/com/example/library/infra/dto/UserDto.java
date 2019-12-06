package com.example.library.infra.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String email;
    private String familyName;
    private String givenName;
}
