package com.josecarlosroman.basics.spring.boot.rest.demo.services;

import com.josecarlosroman.basics.spring.boot.rest.demo.dao.EmployeeRepository;
import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll(); // delegates call to the DAO
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id); // Java 8
        Employee theEmployee = null;
        if (result.isPresent()) theEmployee = result.get();
        else throw new RuntimeException("Employee Not Found");
        return theEmployee;
    }

    @Override
    // @Transactional // JpaRepository provides this functionality out of the box
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    // @Transactional // JpaRepository provides this functionality out of the box
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
