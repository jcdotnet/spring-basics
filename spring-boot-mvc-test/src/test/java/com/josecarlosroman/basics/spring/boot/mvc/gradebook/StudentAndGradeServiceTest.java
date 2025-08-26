package com.josecarlosroman.basics.spring.boot.mvc.gradebook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Test
    public void createStudentService() {
        // studentService.createStudent("Harry", "Potter", "harrypotter@owls.com");
        // HogwartsStudent student = studentDAO.findByEmailAddress("harrypotter@owls.com");
        // assertEquals("harrypotter@owls.com", student.getEmailAddress(), "Should find by email");
    }
}
