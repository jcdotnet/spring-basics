package com.josecarlosroman.basics.spring.boot.mvc.gradebook;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.*;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.HistoryGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.MathGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.ScienceGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.services.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc; // helper class to execute jdbc operations
    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private MathGradesDAO mathGradesDAO;
    @Autowired
    private ScienceGradesDAO scienceGradesDAO;
    @Autowired
    private HistoryGradesDAO historyGradesDAO;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;
    @Value("${sql.script.create.grade.math}")
    private String sqlAddMathGrade;
    @Value("${sql.script.create.grade.science}")
    private String sqlAddScienceGrade;
    @Value("${sql.script.create.grade.history}")
    private String sqlAddHistoryGrade;
    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;
    @Value("${sql.script.delete.grade.math}")
    private String sqlDeleteMathGrade;
    @Value("${sql.script.delete.grade.science}")
    private String sqlDeleteScienceGrade;
    @Value("${sql.script.delete.grade.history}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach // let's insert sample data in the database
    public void setupDatabase() {
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
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
        // should also remove the associated grades for the given student
        Optional<MathGrade> mathGrade = mathGradesDAO.findById(1);
        Optional<ScienceGrade> scienceGrade = scienceGradesDAO.findById(1);
        Optional<HistoryGrade> historyGrade = historyGradesDAO.findById(1);

        assertTrue(deletedStudent.isPresent(), "Should return true");
        assertTrue(mathGrade.isPresent(), "Should return true");
        assertTrue(scienceGrade.isPresent(), "Should return true");
        assertTrue(historyGrade.isPresent(), "Should return true");

        studentService.deleteStudent(1);
        deletedStudent = studentDAO.findById(1);
        mathGrade = mathGradesDAO.findById(1);
        scienceGrade = scienceGradesDAO.findById(1);
        historyGrade = historyGradesDAO.findById(1);

        assertFalse(deletedStudent.isPresent(), "Should return false");
        assertFalse(mathGrade.isPresent(), "Should return false");
        assertFalse(scienceGrade.isPresent(), "Should return false");
        assertFalse(historyGrade.isPresent(), "Should return false");
    }

    @Test
    public void createGradeService() {
        // failing tests for Math
        assertTrue(studentService.createGrade(80.5, 1, "math")); // grade, student ID, subject

        Iterable<MathGrade> mathGrades = mathGradesDAO.findGradeByStudentId(1);

        // failing tests for the remaining subjects
        assertTrue(studentService.createGrade(80.5, 1, "science"));
        assertTrue(studentService.createGrade(80.5, 1, "history"));

        Iterable<ScienceGrade> scienceGrades = scienceGradesDAO.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDAO.findGradeByStudentId(1);

        // TDD, we have to create the service method to create a grade and the mathGradeDAO
        // to find grades by student ID. Once implemented the failed test below must pass
        // i.e. we are able to create a Math grade for the student 1 and get all their grades
        // then we repeat the same for creating Science and History DAO methods and services

        // verifying that the grade exist
        assertTrue(mathGrades.iterator().hasNext(), "Student has Math grades" );

        assertTrue(scienceGrades.iterator().hasNext(), "Student has Science grades" );
        assertTrue(historyGrades.iterator().hasNext(), "Student has History grades" );

        // let's verify instead the student has 2+ grades
        // those tests must pass (one from the beforeEach set up, another added by this test)
        assertTrue(((Collection<MathGrade>) mathGrades).size() >=2,  "Student has 2+ Math grades" );
        assertTrue(((Collection<ScienceGrade>) scienceGrades).size() >=2,  "Student has 2+ Science grades" );
        assertTrue(((Collection<HistoryGrade>) historyGrades).size() >=2,  "Student has 2+ History grades" );
    }

    @Test
    public void createGradeServiceReturnFalse() {
        // invalid grade
        assertFalse(studentService.createGrade(110, 1, "math"));
        assertFalse(studentService.createGrade(-10, 1, "math"));
        // invalid student ID
        assertFalse(studentService.createGrade(80, 2, "math"));
        // invalid subject: they are not learning magic yet
        assertFalse(studentService.createGrade(80, 1, "potions"));
    }

    @Test
    public void deleteGradeService() {
        // failing tests for deleting grades (TDD: implement code that passes all the tests)
        assertEquals(1, studentService.deleteGrade(1, "math"), "Delete Math grade and return the student id");
        assertEquals(1, studentService.deleteGrade(1, "science"), "Delete Science grade and return the student id");
        assertEquals(1, studentService.deleteGrade(1, "history"), "Delete History grade and return the student id");
    }

    @Test
    public void deleteGradeServiceReturnsStudentIdZero() {
        assertEquals(0, studentService.deleteGrade(0, "math"));
        assertEquals(0, studentService.deleteGrade(1, "potions"));
    }

    @Test
    public void studentInformationService() {
        // TDD: failing tests for retrieving the student information // implement & test
        GradebookStudent gradebookStudent = studentService.studentInformation(1);

        assertNotNull(gradebookStudent);
        assertEquals(1, gradebookStudent.getId());
        assertEquals("Draco", gradebookStudent.getFirstname());
        assertEquals("Malfoy", gradebookStudent.getLastname());
        assertEquals(1, gradebookStudent.getStudentGrades().getMathGradeResults().size());
        assertEquals(1, gradebookStudent.getStudentGrades().getScienceGradeResults().size());
        assertEquals(1, gradebookStudent.getStudentGrades().getHistoryGradeResults().size());
    }

    @Test
    public void studentInformationServiceReturnNull() {
        GradebookStudent gradebookStudent = studentService.studentInformation(0);
        assertNull(gradebookStudent);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }
}
