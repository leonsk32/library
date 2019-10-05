package com.example.library.restapi;

import com.example.library.service.BookshelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LibraryController {

    private final BookshelfService service;

    @PatchMapping("books/{isbn10}")
    public ResponseEntity<Void> borrow(@PathVariable("isbn10") String isbn10) {
        service.borrow(isbn10);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
