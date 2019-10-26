package com.example.library.restapi.books;

import com.example.library.app_service.BookSearchService;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.restapi.books.dto.BookListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BooksRestController {
    private final BookSearchService service;

    /**
     * 本の一覧を取得する。
     * 本そのものだけではなく、貸出状況もクエリパラメータでfilterできるようにしたい。
     *
     * @return 本の一覧
     */
    @GetMapping("books")
    public ResponseEntity<BookListDto> search() {
        List<LendingRecord> search = service.search();
        return new ResponseEntity<>(new BookListDto(search), HttpStatus.OK);
    }
}
