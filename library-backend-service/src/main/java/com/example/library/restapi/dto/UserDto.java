package com.example.library.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String email;
    private String simei;
    private String namae;
}
