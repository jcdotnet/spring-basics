package com.josecarlosroman.basics.spring.boot.mvc.crud.services;


import com.josecarlosroman.basics.spring.boot.mvc.crud.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
