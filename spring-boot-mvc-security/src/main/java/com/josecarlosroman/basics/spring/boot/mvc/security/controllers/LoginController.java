package com.josecarlosroman.basics.spring.boot.mvc.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/auth")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }
}
