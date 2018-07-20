package com.yalarifi.hrportal.dto;

import java.util.ArrayList;
import java.util.List;

import com.yalarifi.hrportal.dto.DepartmentDTO;

public class DepartmentDtoFactory {
	
	public static List<DepartmentDTO> mockDepartmentDtoList(int number){
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		for(int i = 0; i< number; i++) {
			DepartmentDTO dept = new DepartmentDTO("d" + number, "dept " + number);
			departments.add(dept);
		}
		return departments;
	}

}
