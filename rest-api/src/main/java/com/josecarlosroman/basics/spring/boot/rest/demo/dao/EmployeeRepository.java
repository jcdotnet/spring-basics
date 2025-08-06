package com.josecarlosroman.basics.spring.boot.rest.demo.dao;

import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// extends JpaRepository<Entity, PrimaryKey>
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it!
}
