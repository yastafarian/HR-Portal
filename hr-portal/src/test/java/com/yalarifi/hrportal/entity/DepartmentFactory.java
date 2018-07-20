package com.yalarifi.hrportal.entity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFactory {
	
	public static List<Department> mockEmptyList() {
		return new ArrayList<Department>();
	}
	
	public static List<Department> mockDepartmentList(int number) {
		List<Department> departments = new ArrayList<Department>();
		for(int i = 0; i< number; i++) {
			Department dept = new Department("d" + number, "dept " + number);
			departments.add(dept);
		}
		return departments;
	}

}
