package com.example.sale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/ok")
    public String getHelloWorld() {
        return "Hello World";
    }
}
