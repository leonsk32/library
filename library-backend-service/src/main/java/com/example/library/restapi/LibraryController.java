package com.example.library.restapi;

import com.example.library.biz.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sample")
@RequiredArgsConstructor
public class BookshelfController {

    private final SampleService service;

    @GetMapping("/hoge")
    public int sample() {
        return service.execute();
    }

}
