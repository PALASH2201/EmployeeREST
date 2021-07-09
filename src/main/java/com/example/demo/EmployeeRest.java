package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeRest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer empId;
	String empName;
	String empDept;
	int empSalary;
	public EmployeeRest() {
		// TODO Auto-generated constructor stub
		
	}
	public EmployeeRest(String empName, String empDept, int empSalary) {
		super();
		this.empName = empName;
		this.empDept = empDept;
		this.empSalary = empSalary;
	}

	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "EmployeeRest [empId=" + empId + ", empName=" + empName + ", empDept=" + empDept + ", empSalary="
				+ empSalary + "]";
	}

	
}
