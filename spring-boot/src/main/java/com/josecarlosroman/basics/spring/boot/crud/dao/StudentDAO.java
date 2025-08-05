package com.josecarlosroman.basics.spring.boot.crud.dao;

import com.josecarlosroman.basics.spring.boot.crud.entities.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student); // C(RUD)

    // primitive types (int) do not have the ability to be null
    Student findById(Integer id); // (C)R(UD)
    List<Student> findAll(); // (C)R(UD)
    List<Student> findByLastName(String lastName); // (C)R(UD)

    void update(Student student); // (CR)U(D)

    void delete(Integer id); // (CRU)D
    int deleteAll(); // (CRU)D
}

