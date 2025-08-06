// using Spring Data JPA instead of the EmployeeDAO interface

/*
package com.josecarlosroman.basics.spring.boot.rest.demo.services;

import com.josecarlosroman.basics.spring.boot.rest.demo.dao.EmployeeDAO;
import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceOldDaoImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceOldDaoImpl(EmployeeDAO employeeDAO) { // ctor injection {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll(); // delegates call to the DAO
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
*/