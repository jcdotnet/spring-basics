package com.josecarlosroman.basics.spring.boot.mvc.gradebook.services;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
