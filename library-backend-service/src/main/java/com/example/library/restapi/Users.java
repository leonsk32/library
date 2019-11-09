package com.example.library.restapi;

import com.example.library.restapi.dto.BookListDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/tmp")
@RequiredArgsConstructor
public class Users {
    @GetMapping("users")
    public ResponseEntity<BookListDto> search() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("users/{userId}")
    public ResponseEntity<Void> register(@PathVariable("userId") String userId, @RequestBody @Valid RequestParam body) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Data
    private static class RequestParam {
        @NotNull
        private String userId;
        @NotNull
        private String email;
        @NotNull
        private String namae;
        @NotNull
        private String simei;

    }
}
