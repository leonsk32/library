package com.example.library.infra.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnEventDto {
    String isbn;
    String userId;
    LocalDateTime lendingDate;
    int count;
}
