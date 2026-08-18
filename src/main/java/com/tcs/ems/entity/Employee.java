package com.tcs.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@Email(message="Enter valid email ID")
	@NotBlank(message="email cannot be null,empty and space")
	private String email;
	@NotBlank(message="name cannot be null,empty and space")
	private String name;
	@NotBlank(message="department cannot be null,empty and space")
	private String department;
	@Positive(message="Salary should be more than zero")
	private double salary;
}
