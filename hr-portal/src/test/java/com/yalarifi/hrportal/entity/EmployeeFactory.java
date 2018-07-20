package com.yalarifi.hrportal.entity;

import java.util.Date;

public class EmployeeFactory {
	
	public static Employee mockSingleEmployee(int id, Date birthDate, Date hireDate) {
		Employee employee = new Employee();
		
		employee.setEmpNo(id);
		employee.setFirst_name("John");
		employee.setLast_name("Doe");
		employee.setBirth_date(birthDate);
		employee.setHire_date(hireDate);
		employee.setGender("M");
		
		return employee;
	}

}
