package com.yalarifi.hrportal.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yalarifi.hrportal.dto.EmployeeDTO;
import com.yalarifi.hrportal.entity.DepartmentManager;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.repository.DepartmentManagerRepository;
import com.yalarifi.hrportal.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	final Logger logger = LogManager.getLogger();
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentManagerRepository departmentManagerRepository;
	
	
	public EmployeeDTO findByEmpNo(int empNo) {
		Employee employeeEntity = employeeRepository.findById(empNo).orElse(null);
		if (employeeEntity != null)
			return new EmployeeDTO(employeeEntity);
		else
			return null;
	}

	public EmployeeDTO editEmployee(EmployeeDTO employee) {
		Employee newEmployee = employeeRepository.findById(employee.getId()).orElse(null);
		
		if (newEmployee == null)
			return null;
		
		newEmployee.setFirst_name(employee.getFirstName());
		newEmployee.setLast_name(employee.getLastName());
		newEmployee = employeeRepository.save(newEmployee);
		return new EmployeeDTO(newEmployee);
	}

	public boolean deleteEmployee(int empNo) {
		Employee toDelete = employeeRepository.findById(empNo).orElse(null);
		
		
		if (toDelete == null || isCurrentManager(toDelete))
			return false;

		employeeRepository.delete(toDelete);
		return true;
	}
	
	private boolean isCurrentManager(Employee employee) {
		Date now = new Date();
		List<DepartmentManager> managers = departmentManagerRepository.findByEmployee(employee);
		for (DepartmentManager manager : managers) {
			if (manager.getTo_date().after(now)) {
				logger.info("fuck");
				return true;
			}
		}
		return false;
	}
}
