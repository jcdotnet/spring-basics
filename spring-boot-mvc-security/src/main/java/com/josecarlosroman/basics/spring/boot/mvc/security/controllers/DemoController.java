package com.josecarlosroman.basics.spring.boot.mvc.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/leaders")
    public String leaderPage() {
        return "leaders";
    }

    @GetMapping("/admins")
    public String adminsPage() {
        return "admins";
    }
}
