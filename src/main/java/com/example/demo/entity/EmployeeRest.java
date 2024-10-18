package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeRest {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	Long empId;
	@NotEmpty(message = "name is mandatory")
	String empName;
	@NotEmpty(message = "department is mandatory")
	String empDept;
	@NotNull(message = "salary is mandatory")
	int empSalary;
}
