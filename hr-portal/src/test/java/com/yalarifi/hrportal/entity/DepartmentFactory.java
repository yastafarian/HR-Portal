package com.yalarifi.hrportal.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentFactory {
	
	public static List<Department> mockEmptyList() {
		return new ArrayList<Department>();
	}
	
	public static List<Department> mockDepartmentList(int number) throws ParseException {
		List<Department> departments = new ArrayList<Department>();
		
		for(int i = 1; i <= number; i++) {
			Department dept = new Department("d" + i, "dept" + i);
			departments.add(dept);
		}
		return departments;
	}

}
