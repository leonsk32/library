package com.example.library.restapi.books;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BooksIsbnActionsRestController {

    @PostMapping("books/{isbn}/actions")
    public ResponseEntity<Void> actions(@PathVariable("isbn") String isbn
//            , @RequestBody RequestParam body
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private static class RequestParam {
        private String type;

    }
}
