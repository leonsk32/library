package com.example.library.restapi;

import com.example.library.app_service.LendingRecordsService;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.restapi.dto.LendingRecordDto;
import com.example.library.restapi.dto.LendingRecordsDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LendingRecords implements LendingRecordsApi{

    private final LendingRecordsService service;

    /**
     * 貸出中の本とユーザーのリストを返却
     *
     * @return
     */
    @GetMapping("lendingRecords")
    @CrossOrigin
    @Override
    public ResponseEntity<LendingRecordsDto> lendingRecordsGet() {
        List<LendingRecord> result = service.search();
        LendingRecordsDto LendingRecords = convertSearchResult(result);
        return new ResponseEntity<>(LendingRecords, OK);
    }

    /**
     * 借りる
     * @param body userIdとisbnが含まれる
     */
    @PostMapping("lendingRecords")
    @CrossOrigin
    public ResponseEntity<Void> borrow(@RequestBody @Valid RequestParame body) {
        service.borrow(body.getIsbn(), body.getUserId());
        return new ResponseEntity<>(OK);
    }

    // TODO 本来であればURLにIDを仕込むがこの場合はどうする
    @DeleteMapping("lendingRecords")
    @CrossOrigin
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
        LendingRecordDto hoge = new LendingRecordDto();
        hoge.setIsbn("9784798121963");
        hoge.userId("1614629");
        list = Arrays.asList(
          hoge,
        hoge
        );

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
