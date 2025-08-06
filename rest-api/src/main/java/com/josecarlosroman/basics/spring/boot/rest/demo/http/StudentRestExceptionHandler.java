package com.josecarlosroman.basics.spring.boot.rest.demo.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice // this is a real-time use of AOP
public class StudentRestExceptionHandler {
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
    public ResponseEntity<StudentErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {

        StudentErrorResponse error = new StudentErrorResponse();
        // Jackson will take the error and will convert it to JSON accordingly
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
    }
    */
}
