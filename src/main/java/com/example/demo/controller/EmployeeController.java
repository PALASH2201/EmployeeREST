package com.example.demo.controller;

import com.example.demo.entity.EmployeeRest;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeRest>> getAllEmployees() {
        List<EmployeeRest> list = employeeService.findAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRest> getEmployee(@PathVariable Long id) {
        Optional<EmployeeRest> employee = employeeService.findEmployee(id);
        return employee.map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<EmployeeRest> addEmployee(@RequestBody @Valid EmployeeRest employee) {
        EmployeeRest employeeRest = employeeService.addNewEmployee(employee);
        return new ResponseEntity<>(employeeRest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRest> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeRest employeeRest) {
        try {
            EmployeeRest updatedEmployee = employeeService.updateExistingEmployee(id, employeeRest);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dept/{name}")
    public ResponseEntity<List<EmployeeRest>> displayEmpByDept(@PathVariable String name) {
        List<EmployeeRest> list = employeeService.getDepartmentEmployees(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<EmployeeRest>> getEmployeesBySalary() {
        List<EmployeeRest> list = employeeService.getAllEmployeesSorted();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAll() {
        try {
            employeeService.deleteAllEmployees();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
