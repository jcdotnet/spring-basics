package com.josecarlosroman.basics.spring.mvc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(name="/")
    public String showLoginForm() {
        return "login";
    }
}
