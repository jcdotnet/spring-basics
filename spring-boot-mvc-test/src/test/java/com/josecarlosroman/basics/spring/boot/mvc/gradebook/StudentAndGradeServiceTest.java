package com.josecarlosroman.basics.spring.boot.mvc.gradebook;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.services.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc; // helper class to execute jdbc operations
    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentDAO studentDAO;

    @BeforeEach // let's insert sample data in the database
    public void setupDatabase() {
        jdbc.execute("insert into student(firstname, lastname, email_address) values ('Draco', 'Malfoy', 'dracomalfoyr@owls.com')");
    }

    @Test
    public void createStudentService() {
        studentService.createStudent("Harry", "Potter", "harrypotter@owls.com");
        HogwartsStudent student = studentDAO.findByEmailAddress("harrypotter@owls.com");
        assertEquals("harrypotter@owls.com", student.getEmailAddress(), "Should find by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(studentService.checkIfStudentExists(1));
        assertFalse(studentService.checkIfStudentExists(0));
    }

    @Test
    public void deleteStudentService() {
        Optional<HogwartsStudent> deletedStudent = studentDAO.findById(1);
        assertTrue(deletedStudent.isPresent(), "Should return true");

        studentService.deleteStudent(1);
        deletedStudent = studentDAO.findById(1);
        assertFalse(deletedStudent.isPresent(), "Should return false");
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM student");
        jdbc.execute("ALTER TABLE student ALTER COLUMN id RESTART WITH 1");
    }
}
