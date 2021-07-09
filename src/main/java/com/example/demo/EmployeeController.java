package com.example.demo;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
		
	@GetMapping("/employees")
	public List<EmployeeRest> getAllEmployees()
	{
		return (List<EmployeeRest>)employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<EmployeeRest> getEmployee(@PathVariable Integer id){
		Optional<EmployeeRest> emp=employeeRepository.findById(id);
		return emp;
	}
	
	@PostMapping("/employees")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeRest e)
	{
		employeeRepository.save(e);
		return ResponseEntity.status(HttpStatus.OK).body("Employee added");
	}
	

	@PutMapping("/employees/{id}/{name}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") Integer id, @PathVariable("name") String name)
	{
		EmployeeRest employee=employeeRepository.findById(id).get();
		employee.setEmpName(name);
		employeeRepository.save(employee);
		return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
	}
	
	@GetMapping("/employees/dept/{name}")
	public ResponseEntity<List> displayEmpByDept(@PathVariable("name") String empDept)
	{
		List<EmployeeRest> employees=employeeRepository.findByEmpDept(empDept);
		return new ResponseEntity(employees,HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees/sort")
    public List<EmployeeRest> getEmployeesBySalary() {

        return (List<EmployeeRest>) employeeRepository.findByOrderByEmpSalary();
        }
	
	@DeleteMapping("/employees")
	public ResponseEntity<String> deleteAll() {
		employeeRepository.deleteAll();

		return ResponseEntity.status(HttpStatus.OK).body("All Employees deleted");
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id){
		Optional<EmployeeRest> emp=employeeRepository.findById(id);
		if(!emp.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		else {
			EmployeeRest e1 = emp.get();
			employeeRepository.deleteById(id);
			
			return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
		}
	}
	
	
	
}