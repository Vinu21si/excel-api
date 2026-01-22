package com.vinayak.excelapi.controller;

import com.vinayak.excelapi.model.Employee;
import com.vinayak.excelapi.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }
}
