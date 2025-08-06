// using Spring Data REST instead of the Employee service
// results are given in the HATEOAS format
// no need to use a controller
// we get automatically the endpoints (PATCH not given)
// API URL is now localhost:8080
// updated path to /api in the app properties

/*
package com.josecarlosroman.basics.spring.boot.rest.demo.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.josecarlosroman.basics.spring.boot.rest.demo.entities.Employee;
import com.josecarlosroman.basics.spring.boot.rest.demo.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    public EmployeeOldRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee NOT Found");
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0); // just in case they pass an id
        // employee.setId(null); // in case we use Integer
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // mapping for PATCH (partial update on an employee)
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                @RequestBody Map<String, Object> patchPayload) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee NOT Found");
        }
        // throwing exception if the request body contains de id key
        if (patchPayload.containsKey("id")) {
            // 500, to create exception handler for employees (or update existing one)
            throw new RuntimeException("Employee ID not allowed in the request body");
        }
        Employee patchedEmployee = apply(patchPayload, tempEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee NOT Found");
        }
        employeeService.deleteById(employeeId);

        return "Deleted Employee with ID: " + employeeId;
    }
    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        // converting employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        // converts patchPayload object to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        // merging
        employeeNode.setAll(patchNode);
        // converting back from JSON object to Employee object
        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
*/