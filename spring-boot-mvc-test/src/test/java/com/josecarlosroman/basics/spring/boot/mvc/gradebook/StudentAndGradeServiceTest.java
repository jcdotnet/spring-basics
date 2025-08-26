package com.josecarlosroman.basics.spring.boot.mvc.gradebook;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.services.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void createStudentService() {
        studentService.createStudent("Harry", "Potter", "harrypotter@owls.com");
        HogwartsStudent student = studentDAO.findByEmailAddress("harrypotter@owls.com");
        assertEquals("harrypotter@owls.com", student.getEmailAddress(), "Should find by email");
    }
}
