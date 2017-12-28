package com.josecarlosroman.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private final String STUDENT_FORM = "student-form";
	private final String STUDENT_CONFIRMATION = "student-confirmation";
	
	// pre-process all web requests coming into this controller
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true); // removes leading and trailing whitespaces
		
		dataBinder.registerCustomEditor(String.class, ste); // pre-processes every string form data
	}
	
	// shows the student form
	@RequestMapping({"/", "/showForm"})
	public String showForm(Model theModel) {
		
		// create a student object
		Student theStudent = new Student();
		// add student object to the model (Spring Data Binding)
		theModel.addAttribute("student", theStudent);
		
		return STUDENT_FORM;
	}
	
	// processes the form using Spring Data Binding
	@RequestMapping("/processForm-novalidation")
	public String processForm(@ModelAttribute("student") Student theStudent) {
		
		// log the input data
		System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
		
		return STUDENT_CONFIRMATION;
	}
	
	// processes the form using Spring Data Binding and validation
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("student") Student theStudent, BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors())
			return STUDENT_FORM;
		else {
			// log the input data
			System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
			return STUDENT_CONFIRMATION;
		}
	}
}