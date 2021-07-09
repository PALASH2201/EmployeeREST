package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeRest, Integer> {
	public List<EmployeeRest> findByEmpDept(String empDept);
	//public List<EmployeeRest> findAllOrderByempSalaryAsc();
	public List<EmployeeRest> findByOrderByEmpSalary();
}
