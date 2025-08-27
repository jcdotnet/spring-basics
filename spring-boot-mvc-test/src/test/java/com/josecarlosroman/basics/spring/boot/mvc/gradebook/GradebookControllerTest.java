package com.josecarlosroman.basics.spring.boot.mvc.gradebook;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.services.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentAndGradeService serviceMock;

    @Autowired
    private StudentDAO studentDAO;

    @BeforeAll
    public static void beforeAll() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Harry");
        request.setParameter("lastname", "Potter");
        request.setParameter("emailAddress", "harrypotter@owls.com");
    }

    @BeforeEach
    public void beforeEach() {
        jdbc.execute("insert into student(firstname, lastname, email_address) values ('Draco', 'Malfoy', 'dracomalfoyr@owls.com')");
    }

    @Test
    public void getStudentsHttpRequest() throws Exception {
        HogwartsStudent studentOne = new HogwartsStudent("Harry", "Potter", "harrypotter@owls.com");
        HogwartsStudent studentTwo = new HogwartsStudent("Ron", "Weasley", "ronweasley@owls.com");

        List<HogwartsStudent> students = new ArrayList<>(Arrays.asList(studentOne, studentTwo));
        // setting up mock
        when(serviceMock.getGradebook()).thenReturn(students);

        // testing mock
        assertIterableEquals(students, serviceMock.getGradebook());

        // setting up request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // testing request
        ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception {
        HogwartsStudent studentOne = new HogwartsStudent("Hermione", "Granger", "hermione@owls.com");
        List<HogwartsStudent> students = new ArrayList<>(Arrays.asList(studentOne));

        // setting mock for the students service
        when(serviceMock.getGradebook()).thenReturn(students);

        // testing
        assertIterableEquals(students, serviceMock.getGradebook());

        // setting up request
        MvcResult mvcResult = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstname", request.getParameterValues("firstname"))
                .param("lastname", request.getParameterValues("lastname"))
                .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        // testing
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        // passed // we are checking now the data too (not only the view name)
        HogwartsStudent student = studentDAO.findByEmailAddress("harrypotter@owls.com");
        assertNotNull(student, "Student should be found");
    }

    @AfterEach
    public void afterEach() {
        jdbc.execute("DELETE FROM student");
        jdbc.execute("ALTER TABLE student ALTER COLUMN id RESTART WITH 1");
    }

}
