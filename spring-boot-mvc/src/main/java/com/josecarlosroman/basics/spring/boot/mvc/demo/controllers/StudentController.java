package com.josecarlosroman.basics.spring.boot.mvc.demo.controllers;

import com.josecarlosroman.basics.spring.boot.mvc.demo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Value("${provinces}")// injecting from the app properties
    private List<String> provinces;

    @Value("${languages}")// injecting from the app properties
    private List<String> languages;

    @Value("${courses}")// injecting from the app properties
    private List<String> courses;

    @GetMapping("/show-form")
    public String showForm(Model theModel) {
        theModel.addAttribute("student", new Student());
        theModel.addAttribute("provinces", provinces);
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("courses", courses);
        return "student-form";
    }

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        System.out.printf("Student %s %s%n", theStudent.getFirstName(), theStudent.getLastName());
        return "student-confirmation";
    }
}
