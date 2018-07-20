package com.yalarifi.hrportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yalarifi.hrportal.dto.EmployeeDTO;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.loggers.LogExecutionTime;
import com.yalarifi.hrportal.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	final Logger logger = LogManager.getLogger();
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@LogExecutionTime
	public EmployeeDTO findByEmpNo(int empNo) {
		Employee employeeEntity = employeeRepository.findById(empNo).orElse(null);
		if (employeeEntity != null)
			return new EmployeeDTO(employeeEntity);
		else
			return null;
	}

	public EmployeeDTO editEmployee(EmployeeDTO employee) {
		Employee newEmployee = employeeRepository.findByEmpNo(employee.getId());
		newEmployee.setFirst_name(employee.getFirstName());
		newEmployee.setLast_name(employee.getLastName());
		newEmployee = employeeRepository.save(newEmployee);
		return new EmployeeDTO(newEmployee);
	}
}
