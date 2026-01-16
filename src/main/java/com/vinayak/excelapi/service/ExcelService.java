package com.vinayak.excelapi.service;

import com.vinayak.excelapi.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private static final String FILE_PATH = "D:/api-data/employees.xlsx";

    public List<Employee> readEmployees() {

        List<Employee> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Employee emp = new Employee();
                emp.setId((int) row.getCell(0).getNumericCellValue());
                emp.setName(row.getCell(1).getStringCellValue());
                emp.setDepartment(row.getCell(2).getStringCellValue());
                emp.setSalary(row.getCell(3).getNumericCellValue());

                employees.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
