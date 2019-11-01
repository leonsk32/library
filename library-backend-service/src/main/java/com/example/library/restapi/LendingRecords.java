package com.example.library.restapi;

import com.example.library.app_service.LendingRecordsService;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.restapi.books.dto.LendingRecordDto;
import com.example.library.restapi.books.dto.LendingRecordsDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/tmp")
@RequiredArgsConstructor
public class LendingRecords {

    private final LendingRecordsService service;

    /**
     * 貸出中の本とユーザーのリストを返却
     *
     * @return
     */
    @GetMapping("rendingRecords")
    public ResponseEntity<LendingRecordsDto> search() {
        List<LendingRecord> result = service.search();
        LendingRecordsDto lendingRecordsDto = convertSearchResult(result);
        return new ResponseEntity<>(lendingRecordsDto, OK);
    }

    /**
     * 借りる
     * @param body userIdとisbnが含まれる
     */
    @PostMapping("rendingRecords")
    public ResponseEntity<Void> borrow(@RequestBody @Valid RequestParame body) {
        service.borrow(body.getIsbn(), body.getUserId());
        return new ResponseEntity<>(OK);
    }

    // TODO 本来であればURLにIDを仕込むがこの場合はどうする
    @DeleteMapping("rendingRecords")
    public ResponseEntity<Void> delete(@RequestBody @Valid RequestParame body) {
        service.returnn(body.getIsbn(), body.getUserId());
        return new ResponseEntity<>(OK);
    }

    private LendingRecordsDto convertSearchResult(List<LendingRecord> entities) {
        LendingRecordsDto dtos = new LendingRecordsDto();
        List<LendingRecordDto> list = new ArrayList<>();
        for (LendingRecord entity : entities) {
            LendingRecordDto recordDto = new LendingRecordDto();
            recordDto.setIsbn(entity.getBook().getIsbn());
            recordDto.setUserId(entity.getUser().getUserId());
            recordDto.setNamae(entity.getUser().getNamae());
            recordDto.setSimei(entity.getUser().getSimei());
            list.add(recordDto);
        }
        dtos.setLendingRecords(list);
        return dtos;
    }

    @Data
    private static class RequestParame {
        @NotNull
        private String isbn;
        @NotNull
        private String userId;

    }
}
