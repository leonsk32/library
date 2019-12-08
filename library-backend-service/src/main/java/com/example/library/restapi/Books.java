package com.example.library.restapi;


import com.example.library.app_service.BookService;
import com.example.library.domain.book.Book;
import com.example.library.restapi.dto.BooksDto;
import com.example.library.restapi.dto.InventorysDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class Books implements BooksApi {
    private final BookService service;

    // TODO 何冊あるかもほしいかも
    @CrossOrigin
    @Override
    public ResponseEntity<BooksDto> booksGet() {
        List<Book> books = service.searchAll();
        return new ResponseEntity<>(convert(books), OK);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<InventorysDto> booksInventoryVersionGet(Integer version) {
        return new ResponseEntity<>(NOT_IMPLEMENTED);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> booksInventoryVersionIsbnDelete(Integer version, Integer isbn) {
        return new ResponseEntity<>(NOT_IMPLEMENTED);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> booksInventoryVersionIsbnPut(Integer version, Integer isbn) {
        return new ResponseEntity<>(NOT_IMPLEMENTED);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> booksInventoryVersionPut(Integer version) {
        return new ResponseEntity<>(NOT_IMPLEMENTED);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> booksIsbnPut(String isbn) {
        service.register(isbn);
        return new ResponseEntity<>(OK);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> booksIsbnDelete(String isbn) {
        service.waste(isbn);
        return new ResponseEntity<>(OK);
    }

    private BooksDto convert(List<Book> books) {
        BooksDto bookListDto = new BooksDto();
        for (Book book : books) {
            bookListDto.addIsbnsItem(book.getIsbn());
        }
        return bookListDto;
    }
}
