package com.josecarlosroman.basics.spring.boot.rest.demo.services;

import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
