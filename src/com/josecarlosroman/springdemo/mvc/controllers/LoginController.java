package com.josecarlosroman.springdemo.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(name="/login")
    public String showLoginForm() {
        return "login";
    }
}
