package com.yalarifi.hrportal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yalarifi.hrportal.dto.EmployeeDTO;
import com.yalarifi.hrportal.dto.EmployeeDtoFactory;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.entity.EmployeeFactory;
import com.yalarifi.hrportal.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {
	
	@Mock
	private EmployeeRepository employeeRepo;
	
	@InjectMocks
	private EmployeeService testee;
	
	@Test
	public void findByEmpNo_SingleValueTest() {
		int id = 1;
		Date birthDate = Date.valueOf("1987-01-01");
		Date hireDate = Date.valueOf("2010-08-01");
		
		Employee emp = EmployeeFactory.mockSingleEmployee(id, birthDate, hireDate);
		when(employeeRepo.findByEmpNo(1)).thenReturn(emp);
		EmployeeDTO expected = EmployeeDtoFactory.mockSingleEmployee(id, birthDate, hireDate);
		
		assertEquals(testee.findByEmpNo(id), expected);
	}
	
	@Test
	public void editEmployee_Test() {
		int id = 1;
		Date birthDate = Date.valueOf("1987-01-01");
		Date hireDate = Date.valueOf("2010-08-01");
		Employee employee = EmployeeFactory.mockSingleEmployee(id, birthDate, hireDate);
		EmployeeDTO employeeDTO = EmployeeDtoFactory.mockSingleEmployee(id, birthDate, hireDate);
		
		when(employeeRepo.save(employee)).thenReturn(employee);
		when(employeeRepo.findByEmpNo(employeeDTO.getId())).thenReturn(employee);
		
		assertEquals(testee.editEmployee(employeeDTO), employeeDTO);
	}
	

}
