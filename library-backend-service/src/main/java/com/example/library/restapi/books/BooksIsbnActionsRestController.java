package com.example.library.restapi.books;

import com.example.library.domain.LibrarianService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Validated
public class BooksIsbnActionsRestController {
    private final LibrarianService service;

    private static final String BORROW = "borrow";

    @PostMapping("books/{isbn}/actions")
    public ResponseEntity<Void> actions(@PathVariable("isbn") String isbn, @RequestBody @Valid RequestParam body) {
        switch (body.getType()){
            case BORROW:
                service.lent(isbn, body.getUserId());
            default:
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



    // FIXME: setterはいらないけど、valueだときかなかった。
    @Data
    private static class RequestParam {
        @NotNull
        private String type;
        @NotNull
        private String userId;

    }
}
