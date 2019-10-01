package com.example.library.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sample")
public class SampleController {

    @GetMapping("/hello")
    public String sample() {
        return "Hello, World!";
    }
}
