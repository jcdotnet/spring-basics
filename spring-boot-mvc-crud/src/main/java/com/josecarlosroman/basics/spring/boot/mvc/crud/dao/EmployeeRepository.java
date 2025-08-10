package com.josecarlosroman.basics.spring.boot.mvc.crud.dao;

import com.josecarlosroman.basics.spring.boot.mvc.crud.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it!

    // adding new query methods
    public List<Employee> findAllByOrderByLastNameAsc();

}
