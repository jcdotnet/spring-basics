package com.josecarlosroman.basics.spring.mvc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("/")
	public String showPage() {
		return "home";
	}
}
