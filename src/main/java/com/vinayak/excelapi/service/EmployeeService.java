package com.vinayak.excelapi.service;

import com.vinayak.excelapi.model.Employee;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final ExcelService excelService;

    public EmployeeService(ExcelService excelService) {
        this.excelService = excelService;
    }

    public List<Employee> getEmployees() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String role = auth.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        if (!role.equals("ROLE_ADMIN") && !role.equals("ROLE_MANAGER")) {
            throw new AccessDeniedException("Access denied");
        }

        return excelService.readEmployees();
    }
}
