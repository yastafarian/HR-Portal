package com.yalarifi.hrportal.dto;

import java.util.Date;


public class EmployeeDtoFactory {
	
	public static EmployeeDTO mockSingleEmployee(int id, Date birthDate, Date hireDate) {
		EmployeeDTO employee = new EmployeeDTO();
		
		employee.setId(id);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setBirthDate(birthDate);
		employee.setHireDate(hireDate);
		employee.setGender("M");
		
		return employee;
	}
}
