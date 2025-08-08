package com.josecarlosroman.basics.spring.boot.mvc.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("theDate", java.time.LocalDateTime.now());
        return "hello";
    }

    @GetMapping("/hello-form")
    public String sayHello(@RequestParam("name") String theName, Model model) {
        model.addAttribute("theMessage", theName != null ? "Yo! " + theName.toUpperCase() : "");
        model.addAttribute("theDate", java.time.LocalDateTime.now());
        return "hello-form";
    }

    // without using @RequestParam
    @GetMapping("/hello-form-2")
    public String sayHello2(HttpServletRequest request, Model model) {
        String theName = request.getParameter("name");
        model.addAttribute("theMessage", theName != null ? "Yo! " + theName.toUpperCase() : "");
        model.addAttribute("theDate", java.time.LocalDateTime.now());
        return "hello-form";
    }

}
