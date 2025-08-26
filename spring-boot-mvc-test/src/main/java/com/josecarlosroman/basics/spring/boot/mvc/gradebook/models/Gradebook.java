package com.josecarlosroman.basics.spring.boot.mvc.gradebook.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Gradebook {

    private List<GradebookStudent> students = new ArrayList<>();

    public Gradebook() {

    }
    public Gradebook(List<GradebookStudent> students) {
        this.students = students;
    }

    public List<GradebookStudent> getStudents() {
        return students;
    }

    public void setStudents(List<GradebookStudent> students) {
        this.students = students;
    }
}
