package com.andrewborchenko.spring.rest.controller;

import com.andrewborchenko.spring.rest.entity.Employee;
import com.andrewborchenko.spring.rest.exception_handling.EmployeeIncorrectData;
import com.andrewborchenko.spring.rest.exception_handling.NoSuchEmployeeException;
import com.andrewborchenko.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*согласно нормам для каждой команды будет содержаться в url /api*/
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    /*так как получение всех работников это get запрос
     с помощью jackson будет собран JSON*/
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }
    /*запрос get где вместо id может быть id любого работника*/
    @GetMapping("/employees/{id}")
    /*аннотация PathVariable нужна для получения id работника из url адреса запроса
    *  /employees/{id} */
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee in " +
                    "database with ID = " + id);
        }
        return employee;
    }

    /*связываем request /employees(post) с методом addNewEmployee*/
    @PostMapping("/employees")
    // при помощи аннотации @RequestBody jackson и spring конвертируют запрос в
    // объект Employee и извлекают его из post запроса
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        // добавление нового в базу не будет так как service uses method saveOrUpdate
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            throw new NoSuchEmployeeException("There is no employee" +
                    " in database with ID = " + id);
        }
        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted";
    }
}
