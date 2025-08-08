package com.josecarlosroman.basics.spring.boot.mvc.demo.model;

import com.josecarlosroman.basics.spring.boot.mvc.demo.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min=2, message = "must be 2 characters long")
    private String lastName;

    // field type must be Integer, not int (because of the TrimEditor)
    @NotNull(message = "is required")
    @Min(value = 0,  message = "must be greater than or equals to zero")
    @Max(value = 10,  message = "must be less than or equals to zero")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "must contain 5 characters")
    private String postalCode;

    @CourseCode // custom annotation for validation
    private String courseCode;

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

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
