package com.example.library.restapi.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class BooksIsbnRestController {

    @PutMapping("books/{isbn}")
    public ResponseEntity<Void> register(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("books/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
