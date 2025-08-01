package com.josecarlosroman.basics.spring.mvc.demo;

import java.util.LinkedHashMap;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Student {
		
	@NotNull(message="is required")
	@Size(min=2, message="at least two characters are required")
	// Spaces are trimmed in the InitBinder, either way, we can use regexs
	// @Pattern(regexp="...", message="...")
	private String firstName;
	
	private String lastName;
	private String province;	
	
	@NotNull(message="is required")	
	private String favoriteLanguage;
	
	@Size(min=1, message="You must take at least one course")
	private String[] takenCourses;
	
	// province and course options
	private LinkedHashMap<String, String> provinces, courses;
	
	public Student() {
		
		// populate provinces
		provinces = new LinkedHashMap<>();
		
		provinces.put("Almeria", "Almería");
		provinces.put("Cadiz", "Cádiz");
		provinces.put("Cordoba", "Córdoba");
		provinces.put("Granada", "Granada");
		provinces.put("Huelva", "Huelva");
		provinces.put("Jaen", "Jaén");
		provinces.put("Malaga", "Málaga");
		provinces.put("Sevilla", "Sevilla");		
		
		// populate courses
		courses = new LinkedHashMap<>();
		
		courses.put("Java SE", "Java SE");
		courses.put("Java EE", "Java Enterprise");
		courses.put("Spring", "Spring");
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public String getProvince() {
		return province;
	}
	
	public String getProvinceValue() {
		return provinces.get(province);
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String[] getTakenCourses() {
		return takenCourses;
	}

	public void setTakenCourses(String[] takenCourses) {
		this.takenCourses = takenCourses;
	}
	

	public LinkedHashMap<String, String> getProvinces() {
		return provinces;
	}	

	public LinkedHashMap<String, String> getCourses() {
		return courses;
	}





}
