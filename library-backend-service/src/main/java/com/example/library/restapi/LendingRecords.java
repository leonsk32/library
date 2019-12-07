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
    @CrossOrigin
    @Override
    public ResponseEntity<LendingRecordsDto> lendingRecordsGet() {
        // TODO レンディングレコードDTOをクエリモデルとして扱う
        // TODO => DTOとエンティティの区別がいらない
        // TODO => リポジトリもいらない　＝＞　レンディングレコードとは毎回作られるもの
        List<LendingRecord> result = service.searchForEvent();
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

    // TODO かりてないとき返そうとしたらエラーにする
    // デリートは基本的にはリクエストボディを持たないらしいので変えた
    @DeleteMapping("lendingRecords/{isbn}/{userId}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable String isbn, @PathVariable String userId) {
        service.returnn(isbn, userId);
        return new ResponseEntity<>(OK);
    }

    private LendingRecordsDto convertSearchResult(List<LendingRecord> entities) {
        LendingRecordsDto dtos = new LendingRecordsDto();
        List<LendingRecordDto> list = new ArrayList<>();
        for (LendingRecord entity : entities) {
            LendingRecordDto recordDto = new LendingRecordDto();
            recordDto.setIsbn(entity.getBook().getIsbn());
            recordDto.setUserId(entity.getUser().getUserId());
            recordDto.setNamae(entity.getUser().getGivenName());
            recordDto.setSimei(entity.getUser().getFamilyName());
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
