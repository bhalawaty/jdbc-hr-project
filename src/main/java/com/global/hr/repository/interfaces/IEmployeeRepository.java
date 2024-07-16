package com.global.hr.repository.interfaces;

import com.global.hr.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    int count();

    Employee findById(Long id);

    List<Employee> findAll();

    List<Employee> findByName(String name);

    int insert(Employee employee);

    int update(Employee employee);

    int delete(Long id);
}
