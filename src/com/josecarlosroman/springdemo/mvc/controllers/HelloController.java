package com.josecarlosroman.springdemo.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	// renders hello world
	@RequestMapping("/")
	public String HelloWorld() {
		return "hello-home";
	}
	
	// shows the initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "hello-form";
	}
	
	// processes the HTML form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	// processes the HTML form adding data to the Spring Model
	@RequestMapping("/processForm2")
	public String letsShout(HttpServletRequest request, Model model) {
		
		// read the request parameter from the HTML form
		String theName = request.getParameter("studentName");
		
		// convert the data to all caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Yo! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		return "hello";
	}
	
	// processes the HTML form adding data to the Spring Model
	// using request binding instead of the HTTPServletRequest
	@RequestMapping("/processForm3")
	public String letsShout(@RequestParam("studentName") String theName, Model model) {
				
		// convert the data to all caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Hello " + theName + "!";
		
		// add message to the model
		model.addAttribute("message", result);
		
		return "hello";
	}
}
