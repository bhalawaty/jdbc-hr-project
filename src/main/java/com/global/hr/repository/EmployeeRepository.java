package com.global.hr.repository;

import com.global.hr.mappers.EmployeeMapper;
import com.global.hr.model.Employee;
import com.global.hr.repository.interfaces.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRepository implements IEmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM employees";
        return jdbcTemplate.queryForObject(sql, new EmptySqlParameterSource(), Integer.class);
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("select id,name,salary from employees where id = :id", new MapSqlParameterSource("id", id),
                new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select id,name,salary from employees", new EmployeeMapper());
    }

    @Override
    public List<Employee> findByName(String name) {
        return jdbcTemplate.query("select id,name,salary from employees where name like   :name", new MapSqlParameterSource("name", "%" + name + "%"), new EmployeeMapper());
    }

    @Override
    public int insert(Employee employee) {
        try {
            return jdbcTemplate.update(
                    "INSERT INTO employees (id, name, salary) VALUES (:id, :name, :salary)",
                    new BeanPropertySqlParameterSource(employee)
            );
        } catch (DataAccessException e) {
            System.err.println("Error inserting employee: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("update employees set  :name, :salary", new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from employees where id = :id", new MapSqlParameterSource("id", id));
    }
}
