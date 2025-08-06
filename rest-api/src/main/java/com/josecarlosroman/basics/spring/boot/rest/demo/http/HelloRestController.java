package com.josecarlosroman.basics.spring.boot.rest.demo.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloRestController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
}
