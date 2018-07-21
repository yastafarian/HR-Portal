package com.yalarifi.hrportal.dto;

import java.util.List;


import com.yalarifi.hrportal.entity.Department;
import com.yalarifi.hrportal.entity.DepartmentManager;
import com.yalarifi.hrportal.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDTO {
	
	public DepartmentDTO(Department newDepartmentEntity) {
		
		
		List<DepartmentManager> managers = newDepartmentEntity.getCurrentManager();
		
		if (managers != null && !managers.isEmpty()){
			Employee currentManager = managers.get(0).getEmployee();
			this.currentManager = currentManager.getFirst_name() + " " + currentManager.getLast_name();
		}
		else {
			this.currentManager = "Vacant";
		}
		
		this.deptName = newDepartmentEntity.getDept_name();
		this.deptNumber = newDepartmentEntity.getDept_no();
	}
	
	public DepartmentDTO() {
		
	}

	private String deptNumber;
	
	private String deptName;
	
	private String currentManager;
}
