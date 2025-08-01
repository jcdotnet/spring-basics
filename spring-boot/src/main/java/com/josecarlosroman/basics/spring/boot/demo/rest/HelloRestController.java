package com.josecarlosroman.basics.spring.boot.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    // expose "/" endpoint that returns "Hello World"
    @GetMapping("/")
    public String SayHello() {
        return "Hello World";
    }

}
