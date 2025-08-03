package com.josecarlosroman.basics.spring.boot.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @Value("${hello.message}")
    private String helloMessage;

    @Value("${beautiful.message}")
    private String beautifulMessage;

    // expose "/" endpoint that returns "Hello World"
    @GetMapping("/")
    public String SayHello() {
        // return "Hello World";
        return helloMessage;
    }

    // expose "/beautiful" endpoint
    @GetMapping("/beautiful")
    public String SayBeautiful() {
        // return "Hello, Beautiful World!";
        return beautifulMessage;
    }
}
