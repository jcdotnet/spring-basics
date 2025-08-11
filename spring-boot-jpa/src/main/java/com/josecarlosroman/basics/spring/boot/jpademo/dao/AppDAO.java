package com.josecarlosroman.basics.spring.boot.jpademo.dao;

import com.josecarlosroman.basics.spring.boot.jpademo.entities.*;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);
    void save(Course course);

    Instructor findInstructorById(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    InstructorDetail findInstructorDetailById(int id);
    List<Course> findCourses(int instructorId);
    Course findCourseById(int id);
    Course findCourseByIdJoinFetch(int id);
    List<Student> findStudentsJoinFetch(int courseId);
    List<Course> findCoursesJoinFetch(int studentId);

    void update(Instructor instructor);
    void update(Course course);

    void deleteInstructorById(int id);
    void deleteInstructorDetailById(int id);
    void deleteStudentById(int id);
    void deleteCourseById(int id);
}
