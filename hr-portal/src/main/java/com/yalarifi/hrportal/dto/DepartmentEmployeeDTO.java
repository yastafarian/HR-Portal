package com.yalarifi.hrportal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentEmployeeDTO {
	
	private String name;
	
	private String fromDate;
	
	private String toDate;
}
