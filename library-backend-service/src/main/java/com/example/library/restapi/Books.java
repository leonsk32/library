package com.example.library.restapi;


import com.example.library.app_service.BookService;
import com.example.library.domain.book.Book;
import com.example.library.restapi.dto.BookListDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tmp")
@RequiredArgsConstructor
public class Books {
    private final BookService service;
    @CrossOrigin
    @GetMapping("books")
    public ResponseEntity<BookListDto> search() {
        List<Book> books = service.searchAll();
        return new ResponseEntity<BookListDto>(convert(books), OK);
    }

    @CrossOrigin
    @PutMapping("books/{isbn}")
    public ResponseEntity<Void> register(@PathVariable("isbn") String isbn) {
        service.register(isbn);
        return new ResponseEntity<>(OK);
    }

    @CrossOrigin
    @DeleteMapping("books/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable("isbn") String isbn) {
        service.waste(isbn);
        return new ResponseEntity<>(OK);
    }

    @Data
    private static class RequestParam {
        @NotNull
        private String title;

    }

    private BookListDto convert(List<Book> books) {
        BookListDto bookListDto = new BookListDto();
        List<String> isbns = new ArrayList<>();
        for(Book book : books) {
            isbns.add(book.getIsbn());
        }
        bookListDto.setIsbns(isbns);
        return bookListDto;
    }

}
