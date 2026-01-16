package com.vinayak.excelapi.controller;

import com.vinayak.excelapi.model.Employee;
import com.vinayak.excelapi.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final ExcelService excelService;

    public EmployeeController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return excelService.readEmployees();
    }
}
