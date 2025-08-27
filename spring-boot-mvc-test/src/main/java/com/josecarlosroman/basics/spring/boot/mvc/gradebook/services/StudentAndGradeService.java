package com.josecarlosroman.basics.spring.boot.mvc.gradebook.services;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDAO studentDAO;

    public void createStudent(String firstName, String lastName, String email) {
        HogwartsStudent student = new HogwartsStudent(firstName, lastName, email);
        student.setId(0);
        studentDAO.save(student);
    }

    public Iterable<HogwartsStudent> getGradebook() {
        return studentDAO.findAll();
    }

    public boolean checkIfStudentExists(int id) {
        Optional<HogwartsStudent> student = studentDAO.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if (checkIfStudentExists(id)) {
            studentDAO.deleteById(id);
        }
    }
}
