package com.example.library.restapi.books;

import com.example.library.app_service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BooksRestController {
    private final LibrarianService service;

    /**
     * 本の一覧を取得する。
     * 本そのものだけではなく、貸出状況もクエリパラメータでfilterできるようにしたい。
     *
     * @return 本の一覧
     */
    @GetMapping("books")
    public ResponseEntity<Void> search() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
