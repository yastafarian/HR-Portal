package com.yalarifi.hrportal.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yalarifi.hrportal.dto.DepartmentDTO;
import com.yalarifi.hrportal.entity.Department;
import com.yalarifi.hrportal.entity.DepartmentManager;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.entity.classid.DepartmentEmployeeID;
import com.yalarifi.hrportal.repository.DepartmentManagerRepository;
import com.yalarifi.hrportal.repository.DepartmentRepository;
import com.yalarifi.hrportal.repository.EmployeeRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentManagerRepository departmentManagerRepository;
	
	public List<DepartmentDTO> findAll(){
		Iterable<Department> depts = departmentRepository.findAll();
		List<DepartmentDTO> deptDTOs = new ArrayList<>();
		
		if (depts == null)
			return deptDTOs;
		
		for (Department dept : depts) {
			DepartmentDTO deptDTO = new DepartmentDTO(dept);
			deptDTOs.add(deptDTO);
		}
		
		return deptDTOs;
	}

	
	public DepartmentDTO addDepartment(String deptNo, String deptName, int empNo) throws ParseException {
		
		//TODO: Review and refactor
		//TODO: Create new unit tests
		//TODO: assign aspects
		//TODO: Run Scenario again
		
		Department newDepartmentEntity = new Department(deptNo, deptName);
		newDepartmentEntity = departmentRepository.save(newDepartmentEntity);
		
		Employee managerEntity = employeeRepository.findById(empNo).orElse(null);
		
		//TODO: check if null
		
		/*
		 * Assume the from_date is always today's date when creating a new department
		 */
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = sdf.parse("9999-01-01");
		
		DepartmentManager deptManager = new DepartmentManager(new DepartmentEmployeeID(empNo, deptNo),
																	newDepartmentEntity, 
																	managerEntity, 
																	fromDate, 
																	toDate);
		
		deptManager = departmentManagerRepository.save(deptManager);
		
		List<DepartmentManager> manager = new ArrayList<DepartmentManager>();
		manager.add(deptManager);
		
		newDepartmentEntity.setCurrentManager(manager);
		
		
		return new DepartmentDTO(newDepartmentEntity);
	}


	public DepartmentDTO changeDepartmentManager(String deptNo, int empNo) throws ParseException {
		Department department = departmentRepository.findById(deptNo).orElse(null);
		Employee employee = employeeRepository.findById(empNo).orElse(null);
		
		//Check if both entries exist
		if (employee.equals(null) || department.equals(null))
			return null;
		
		//Now find current manager and update it's status
		DepartmentManager currentManager = department.getCurrentManager().get(0);
		currentManager.setTo_date(new Date());
		departmentManagerRepository.save(currentManager);
		
		//Create and save new Manager
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = sdf.parse("9999-01-01");
		
		currentManager = new DepartmentManager(new DepartmentEmployeeID(empNo, deptNo),
				department, 
				employee, 
				fromDate, 
				toDate);
		
		departmentManagerRepository.save(currentManager);
		
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		return departmentDTO;
	}
	
}
