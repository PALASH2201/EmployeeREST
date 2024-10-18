package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.EmployeeRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRest, Long> {
	public List<EmployeeRest> findByEmpDept(String empDept);
	public List<EmployeeRest> findByOrderByEmpSalary();
	public void deleteById(Long empId);
	public Optional<EmployeeRest> findById(Long empId);
}
