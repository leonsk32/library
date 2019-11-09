package com.example.library.restapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LendingRecordDto {
    private String userId;
    private String namae;
    private String simei;
    private String isbn;
}
