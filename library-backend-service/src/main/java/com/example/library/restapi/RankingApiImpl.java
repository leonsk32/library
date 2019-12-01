package com.example.library.restapi;

import com.example.library.restapi.dto.RankingsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RankingApiImpl implements RankingApi {
    @Override
    public ResponseEntity<RankingsDto> rankingBooksGet() {
        return new ResponseEntity<>(NOT_IMPLEMENTED);
    }
}
