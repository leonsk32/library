package com.example.library.restapi;

import com.example.library.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class Persons {
    private final PersonService personService;

    /**
     * TODO 冪等な登録にする
     * これはインフラで実現すべきか、ドメインの知識なのか
     * @param personDto
     * @return
     */
    @PutMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> regist(@RequestBody PersonDto personDto) {
        personService.regist(personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
