package com.example.library.restapi;


import com.example.library.app_service.LendingRecordsService;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.restapi.books.dto.BookListDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/tmp")
@RequiredArgsConstructor
public class Books {
    @GetMapping("books")
    public ResponseEntity<BookListDto> search() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO タイトルはAmazonAPIとかからとってくるか、入力するか
    // amountの管理はテーブル、ドメインどうするか
    @PutMapping("books/{isbn}")
    public ResponseEntity<Void> register(@PathVariable("isbn") String isbn, @RequestBody @Valid RequestParam body) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("books/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Data
    private static class RequestParam {
        @NotNull
        private String title;

    }

}
