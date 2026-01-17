package com.vinayak.excelapi.service;

import com.vinayak.excelapi.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    // 👇 FIXED FILE LOCATION
    private static final String FILE_PATH =
            "D:/VS Code/API/excelapi/employees.xlsx";

    public List<Employee> readEmployees() {

        List<Employee> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int idCounter = 1;

            for (Row row : sheet) {

                if (row.getRowNum() == 0) continue;

                Employee emp = new Employee();
                emp.setId(idCounter++);
                emp.setName(row.getCell(0).getStringCellValue());      // Sales Person
                emp.setDepartment(row.getCell(1).getStringCellValue()); // Country
                emp.setSalary(row.getCell(4).getNumericCellValue());   // Amount

                employees.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
