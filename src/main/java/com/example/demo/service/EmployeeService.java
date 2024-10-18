package com.example.demo.service;

import com.example.demo.entity.EmployeeRest;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Retrieve all employees
    public List<EmployeeRest> findAllEmployees() {
        return employeeRepository.findAll();  // No need for try-catch here
    }

    // Find an employee by ID
    public Optional<EmployeeRest> findEmployee(Long id) {
        return employeeRepository.findById(id);  // Spring handles exceptions internally
    }

    // Add a new employee
    public EmployeeRest addNewEmployee(EmployeeRest employeeRest) {
        return employeeRepository.save(employeeRest);  // Spring handles exceptions internally
    }

    // Update an existing employee
    public EmployeeRest updateExistingEmployee(Long id, EmployeeRest employeeRest) {
        Optional<EmployeeRest> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            EmployeeRest existingEmployee = employeeOptional.get();

            // Update fields only if provided in the employeeRest
            if (employeeRest.getEmpName() != null && !employeeRest.getEmpName().isEmpty()) {
                existingEmployee.setEmpName(employeeRest.getEmpName());
            }
            if (employeeRest.getEmpSalary() > 0) {
                existingEmployee.setEmpSalary(employeeRest.getEmpSalary());
            }
            if (employeeRest.getEmpDept() != null && !employeeRest.getEmpDept().isEmpty()) {
                existingEmployee.setEmpDept(employeeRest.getEmpDept());
            }

            // Save the updated employee
            return employeeRepository.save(existingEmployee);
        } else {
            throw new IllegalArgumentException("Employee with id " + id + " not found");
        }
    }

    // Get all employees by department
    public List<EmployeeRest> getDepartmentEmployees(String deptName) {
        List<EmployeeRest> employees = employeeRepository.findByEmpDept(deptName);
        if (!employees.isEmpty()) {
            return employees;
        } else {
            throw new RuntimeException("No employees found in department: " + deptName);
        }
    }

    // Get all employees sorted by salary
    public List<EmployeeRest> getAllEmployeesSorted() {
        List<EmployeeRest> employees = employeeRepository.findByOrderByEmpSalary();
        if (!employees.isEmpty()) {
            return employees;
        } else {
            throw new RuntimeException("No employees found");
        }
    }

    // Delete all employees
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();  // No need for exception since empty repository won't cause issues
    }

    // Delete an employee by ID
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);  // If the ID doesn't exist, Spring handles it internally
    }
}
