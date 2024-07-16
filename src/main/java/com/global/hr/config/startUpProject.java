package com.global.hr.config;

import com.global.hr.model.Employee;
import com.global.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class startUpProject implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE IF EXISTS EMPLOYEES");
        jdbcTemplate.execute("CREATE TABLE EMPLOYEES(Id SERIAL,name varchar(255),salary NUMERIC(15,2))");

        if (employeeRepository.count() == 0){
            employeeRepository.insert(new Employee(20L,"bilal",56456.0));
            employeeRepository.insert(new Employee(30L,"AHMED",888.0));
            employeeRepository.insert(new Employee(40L,"MAHMOUD",777.0));
            employeeRepository.insert(new Employee(50L,"reda",666.0));
            employeeRepository.insert(new Employee(90L,"noor",54444.0));
            employeeRepository.insert(new Employee(70L,"fady",4322.0));
            employeeRepository.insert(new Employee(10L,"nada",1111.0));
        }

    }
}
