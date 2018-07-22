package com.yalarifi.hrportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.yalarifi.hrportal.dto.DepartmentDTO;
import com.yalarifi.hrportal.entity.Employee;

public class DepartmentDtoFactory {
	
	public static List<DepartmentDTO> mockDepartmentDtoListWithManager(int number, Employee manager){
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		for(int i = 1; i<= number; i++) {
			DepartmentDTO dept = new DepartmentDTO("d" + number, "dept " + number, manager.getFirst_name() + " " + manager.getLast_name());
			departments.add(dept);
		}
		return departments;
	}
	
	public static List<DepartmentDTO> mockDepartmentDtoList(int number){
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		for(int i = 1; i <= number; i++) {
			DepartmentDTO dept = new DepartmentDTO("dept" + i, "d" + i);
			departments.add(dept);
		}
		return departments;
	}


}
