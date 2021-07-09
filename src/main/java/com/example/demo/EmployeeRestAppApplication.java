package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class EmployeeRestAppApplication implements CommandLineRunner{

	@Autowired
	EmployeeRepository employeeRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeRestAppApplication.class, args);
		System.out.println("Running");
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		employeeRepository.save(new EmployeeRest("ABC","Devops",30000));
		employeeRepository.save(new EmployeeRest("XYZ","Security",10000));
		employeeRepository.save(new EmployeeRest("UVW","Programming",32000));
		employeeRepository.save(new EmployeeRest("GHI","Security",22000));
		System.out.println("Employees added by default");
		
	}

}
