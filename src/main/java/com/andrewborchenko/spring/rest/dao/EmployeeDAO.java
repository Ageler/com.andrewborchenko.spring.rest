package com.andrewborchenko.spring.rest.dao;

import org.springframework.stereotype.Repository;
import com.andrewborchenko.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);
    Employee getEmployee(int id);
    void deleteEmployee(int id);
}
