package com.josecarlosroman.basics.spring.boot.mvc.gradebook.controller;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.Gradebook;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.*;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.services.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

    @Autowired
    private StudentAndGradeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<HogwartsStudent> students = service.getGradebook();
        m.addAttribute("students", students);
        return "index";
	}

    @PostMapping(value="/")
    public String createStudent(@ModelAttribute("student") HogwartsStudent student, Model model) {
        service.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());

        Iterable<HogwartsStudent> students = service.getGradebook();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable int id, Model m) {

        if (!service.checkIfStudentExists(id)) return "error";

        service.deleteStudent(id);
        Iterable<HogwartsStudent> students = service.getGradebook();
        m.addAttribute("students", students);
        return "index";
    }

	@GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
    }

}
