package com.example.library.restapi.books;

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

    @PostMapping("books/{isbn}/actions")
    public ResponseEntity<Void> actions(@PathVariable("isbn") String isbn, @RequestBody @Valid RequestParam body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // FIXME: setterはいらないけど、valueだときかなかった。
    @Data
    private static class RequestParam {
        @NotNull
        private String type;

    }
}
