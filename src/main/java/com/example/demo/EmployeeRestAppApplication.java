package com.example.demo;

import com.example.demo.config.EmployeeConfig;
import com.example.demo.entity.EmployeeRest;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class EmployeeRestAppApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeConfig employeeConfig;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRestAppApplication.class, args);
        System.out.println("Running");
    }

    @Override
    public void run(String... args) throws Exception {
        // Retrieve employees from configuration
        List<EmployeeRest> employees = employeeConfig.getEmployees();

        // Debug output
        System.out.println("Retrieved Employees: " + employees);

        // Validate that the employee list is not null and contains no null entries
        if (employees != null && !employees.isEmpty()) {
            employees.removeIf(Objects::isNull);  // Remove any null entries
            employeeRepository.saveAll(employees);
            System.out.println("Employees added by default");
        } else {
            System.out.println("No employees to add");
        }
    }

}
