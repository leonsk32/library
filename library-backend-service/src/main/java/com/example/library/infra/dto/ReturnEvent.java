package com.example.library.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnEvent {
    String isbn;
    String userId;
    LocalDateTime lendingDate;
    int count;
}
