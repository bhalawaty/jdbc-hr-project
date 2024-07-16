package com.global.hr.controller;

import com.global.hr.model.Employee;
import com.global.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/count")
    public int countEmployees() {
        return employeeRepository.count();
    }


    @GetMapping("/filter/{name}")
    public List<Employee> getEmployees(@PathVariable String name) {
        return employeeRepository.findByName(name);
    }

    @GetMapping("find/{id}")
    public Employee findEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    @PostMapping("/")
    public int addEmployee(Employee employee) {
        return employeeRepository.insert(employee);
    }

    @PostMapping("/{id}")
    public int updateEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id);
        return employeeRepository.update(employee);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteEmployee(@PathVariable Long id) {
        return employeeRepository.delete(id);
    }
}
