package com.josecarlosroman.basics.spring.boot.mvc.gradebook.models;

public class GradebookStudent extends HogwartsStudent {

    private int id;

    private StudentGrades studentGrades;

    public GradebookStudent(String firstname, String lastname, String emailAddress) {
        super(firstname, lastname, emailAddress);
    }

    public GradebookStudent(int id, String firstname, String lastname, String emailAddress, StudentGrades studentGrades) {
        super(firstname, lastname, emailAddress);
        this.studentGrades = studentGrades;
        this.id = id;
    }

    public StudentGrades getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(StudentGrades studentGrades) {
        this.studentGrades = studentGrades;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
