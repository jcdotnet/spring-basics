package com.josecarlosroman.basics.spring.boot.mvc.crud.controllers;

import com.josecarlosroman.basics.spring.boot.mvc.crud.entities.Employee;
import com.josecarlosroman.basics.spring.boot.mvc.crud.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // @Autowired is optional here
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String ListEmployees(Model model) {
        List<Employee> theEmployees = employeeService.findAll();
        model.addAttribute("employees", theEmployees);
        return "employees";
    }

    @GetMapping("/save-form")
    public String CreateEmployee(Model model) {

        model.addAttribute("employee", new Employee());

        return "save-form";
    }

    @GetMapping("/update-form")
    public String UpdateEmployee(@RequestParam("employeeId") int id, Model model) {

        model.addAttribute("employee", new Employee());

        Employee theEmployee = employeeService.findById(id);

        model.addAttribute("employee", theEmployee);

        return "save-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        employeeService.save(theEmployee);

        return "redirect:/employees/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        // delete the employee
        employeeService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/employees/";

    }

}
