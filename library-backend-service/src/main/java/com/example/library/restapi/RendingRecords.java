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

@RestController
@RequestMapping("/tmp")
@RequiredArgsConstructor
public class RendingRecords {

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
        return new ResponseEntity<>(lendingRecordsDto, HttpStatus.OK);
    }

    /**
     * 借りる
     * @param body userIdとisbnが含まれる
     */
    @PostMapping("rendingRecords")
    public ResponseEntity<Void> register(@RequestBody @Valid RequestParam body) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO 本来であればURLにIDを仕込むがこの場合はどうする
    @DeleteMapping("rendingRecords/")
    public ResponseEntity<Void> delete(@RequestBody @Valid RequestParam body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    private LendingRecordsDto convertSearchResult(List<LendingRecord> entities) {
        LendingRecordsDto dtos = new LendingRecordsDto();
        List<LendingRecordDto> list = new ArrayList<>();
        for (LendingRecord entity : entities) {
            LendingRecordDto recordDto = new LendingRecordDto();
            recordDto.setIsbn(entity.getBook().getIsbn());
            recordDto.setTitle(entity.getBook().getTitle());
            recordDto.setUserId(entity.getUser().getUserId());
            recordDto.setNamae(entity.getUser().getNamae());
            recordDto.setSimei(entity.getUser().getSimei());
        }
        dtos.setLendingRecords(list);
        return dtos;
    }

    @Data
    private static class RequestParam {
        @NotNull
        private String isbn;
        @NotNull
        private String userId;

    }
}
