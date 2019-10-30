package com.example.library.restapi.books.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LendingRecordsDto {
    @JsonProperty("lendingRecord")
    private  List<LendingRecordDto> lendingRecords;
}
