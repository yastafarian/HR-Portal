package com.yalarifi.hrportal.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.yalarifi.hrportal.entity.DepartmentEmployee;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.entity.Salary;
import com.yalarifi.hrportal.entity.Title;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
	
	private static final Logger logger = Logger.getLogger(EmployeeDTO.class.getName());
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;

	private String gender;
	
	private Date hireDate;
	
	private int currentSalary;
	
	private String currentTtile;
	
	private List<DepartmentEmployee> departments;
	
	public EmployeeDTO(Employee entity) {
		List<Title> titles = entity.getTitles();
		List<Salary> salaries = entity.getSalaries();
		Date now = new Date();
		
		if (!titles.equals(null) && titles.size() > 0) {
			for (Title title : titles) {
				if (title.getToDate().after(now)) {
					this.currentTtile = title.getId().getTitle();
				}
			}
		}
		
		if (!salaries.equals(null) && salaries.size() > 0) {
			for(Salary salary : salaries) {
				if (salary.getToDate().after(now)) {
					this.currentSalary = salary.getSalary();
				}
			}
		}
		
		this.id = entity.getEmpNo();
		this.firstName = entity.getFirst_name();
		this.lastName = entity.getLast_name();
		this.birthDate = entity.getBirth_date();
		this.gender = entity.getGender();
		this.hireDate = entity.getHire_date();
		
		
		this.departments = entity.getDepartments();
	}
	
	public EmployeeDTO() {
		//Had Lombok issues so i had to manually make a no args constructor
	}
	
	public Map<String, Object> getModel(){
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("id", this.id);
		model.put("firstName", this.firstName);
		model.put("lastName", this.lastName);
		model.put("birthDate", this.birthDate.toString());
		model.put("gender", this.gender);
		model.put("hireDate", this.hireDate);
		List<DepartmentEmployeeDTO> departments = new ArrayList<>();
		for (DepartmentEmployee dept : this.departments) {
			departments.add(new DepartmentEmployeeDTO(dept.getDepartment().getDept_name(), 
														dept.getFrom_date().toString(), 
														dept.getTo_date().toString()));
		}
		model.put("departments", departments);
		model.put("currentTitle", this.currentTtile);
		model.put("currentSalary", this.currentSalary);
		
		return model;
	}
}
