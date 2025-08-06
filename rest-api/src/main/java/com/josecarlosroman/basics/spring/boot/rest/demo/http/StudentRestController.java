package com.josecarlosroman.basics.spring.boot.rest.demo.http;

import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct // loads the student data only once
    public void loadData(){
        students = new ArrayList<>();
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Doe"));
        students.add(new Student("Nick", "Doe"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students; // Jackson will convert the list of POJOS to a JSON array
    }

    @GetMapping("students/{studentId}") // studentId is a path variable
    public Student getStudent(@PathVariable int studentId){ // binding to param id

        if (studentId < 0 || ( studentId >= students.size() )) {
            throw new StudentNotFoundException("Student with ID " + studentId + "Not Found");
        }
        return students.get(studentId);
    }

    // Exception Handling // using GLOBAL exception handling instead
    /*
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {

        StudentErrorResponse error = new StudentErrorResponse(); // POJO
        // Jackson will take the error and will convert it to JSON accordingly
        error.setStatus(HttpStatus.NOT_FOUND.value()); // error.setStatus(404);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // body and status code
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        // Jackson will take the error and will convert it to JSON accordingly
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    */
}
