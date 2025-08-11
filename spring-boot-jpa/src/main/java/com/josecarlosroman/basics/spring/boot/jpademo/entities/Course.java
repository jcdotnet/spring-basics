package com.josecarlosroman.basics.spring.boot.jpademo.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    // One-To-Many unidirectional relationship
    // Fetch type: LAZY (default)
    // cascade ALL: if delete a course we want to remove the reviews
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    // Many-To-One relationship
    // Fetch type: EAGER (default for One-To-One and Many-To-One)
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinColumn(name="instructor_id")
    Instructor instructor;

    // Many-To-Many relationship
    @ManyToMany(
            //fetch = FetchType.LAZY, // default for @ManyToMany is LAZY
            cascade = {
                CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinTable( // provides mapping between the two tables
            name="course_student",
            joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private List<Student> students;

    public Course(){
    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // helper methods
    public void addReview(Review review) {

        if (reviews == null) reviews =new ArrayList<>();

        reviews.add(review);
    }

    public void addStudent(Student student) {

        if (students == null) students =new ArrayList<>();

        students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
