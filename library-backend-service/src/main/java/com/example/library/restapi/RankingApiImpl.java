package com.example.library.restapi;

import com.example.library.restapi.dto.RankingsDto;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public class RankingApiImpl implements RankingApi {
    @Override
    public ResponseEntity<RankingsDto> rankingBooksGet() {
        return new ResponseEntity<>(null, NOT_IMPLEMENTED);
    }
}
