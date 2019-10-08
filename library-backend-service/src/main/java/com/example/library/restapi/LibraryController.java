package com.example.library.restapi;

import com.example.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LibraryController {
    private final BorrowService borrowService;

    @PostMapping(value = "/book_deal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> borrow(@RequestBody BookDealDto bookDealDto) {
        borrowService.borrow(bookDealDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
