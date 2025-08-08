package com.josecarlosroman.basics.spring.boot.mvc.demo.model;

import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private String province;
    private String favoriteLanguage;
    private List<String> takenCourses;

    public Student(){

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public List<String> getTakenCourses() {
        return takenCourses;
    }

    public void setTakenCourses(List<String> takenCourses) {
        this.takenCourses = takenCourses;
    }
}
