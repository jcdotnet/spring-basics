package com.josecarlosroman.basics.spring.boot.crud;

import com.josecarlosroman.basics.spring.boot.crud.dao.StudentDAO;
import com.josecarlosroman.basics.spring.boot.crud.entities.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootCRUDApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.josecarlosroman.basics.spring.boot.crud.SpringBootCRUDApplication.class, args);
    }

    // Command Line App
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            System.out.println("Hello CRUD App");
            createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            readStudent(studentDAO);
            readStudents(studentDAO);
            readStudentsByLastName(studentDAO);
            updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteStudents(studentDAO);
        };
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        Student student1 = new Student("John", "Doe", "john@email.com");
        Student student2 = new Student("Jane", "Doe", "jane@email.com");
        Student student3 = new Student("Nick", "Doe", "nick@email.com");

        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);

        System.out.printf("Students IDs = %s %s %s", student1.getId(), student2.getId(), student3.getId());
    }

    private void createStudent(StudentDAO studentDAO) {

        // create the student object
        Student tempStudent = new Student("David", "Doe", "david@email.com");

        // save the student object
        studentDAO.save(tempStudent);

        // display saved student (ID)
        System.out.println("Student ID = " + tempStudent.getId());
    }

    private void readStudent(StudentDAO studentDAO) {
        Student tempStudent = new Student("Peter", "Doe", "peter@email.com");
        studentDAO.save(tempStudent);

        int theId = tempStudent.getId();
        System.out.println("Student ID: " + theId);

        // retrieve student based on the id: primary key
        Student student = studentDAO.findById(theId);
        System.out.println("Found student " + student.getId());
    }

    private void readStudents(StudentDAO studentDAO) {
        // createStudents(studentDAO);
        List<Student> theStudents = studentDAO.findAll();

        for (Student tempStudent : theStudents) {
            System.out.println("Found student " + tempStudent);
        }
    }

    private void readStudentsByLastName(StudentDAO studentDAO) {
        // createStudents(studentDAO);
        List<Student> theStudents = studentDAO.findByLastName("Doe");

        for (Student tempStudent : theStudents) {
            System.out.println("Found student with Doe last name: " + tempStudent);
        }
    }

    private void updateStudent(StudentDAO studentDAO) {
        // retrieve student based on the id: primary key
        Student student = studentDAO.findById(1);

        // change the first name to "Alice"
        student.setFirstName("Alice");

        // update student
        studentDAO.update(student);

        System.out.println("Updated student: " + student);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 1;
        System.out.println("Deleting student " + studentId);
        studentDAO.delete(studentId);
    }

    private void deleteStudents(StudentDAO studentDAO) {
        int deletedStudents = studentDAO.deleteAll();
        System.out.printf("Deleted %s students", deletedStudents);
    }

}
