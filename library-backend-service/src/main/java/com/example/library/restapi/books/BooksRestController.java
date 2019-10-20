package com.example.library.restapi.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class BooksRestController {

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
