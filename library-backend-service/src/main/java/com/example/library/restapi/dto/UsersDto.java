package com.example.library.restapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersDto {
    private List<UserDto> users;
}
