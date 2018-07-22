package com.yalarifi.hrportal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yalarifi.hrportal.dto.DepartmentDTO;
import com.yalarifi.hrportal.dto.DepartmentDtoFactory;
import com.yalarifi.hrportal.entity.Department;
import com.yalarifi.hrportal.entity.DepartmentFactory;
import com.yalarifi.hrportal.entity.DepartmentManager;
import com.yalarifi.hrportal.entity.Employee;
import com.yalarifi.hrportal.entity.classid.DepartmentEmployeeID;
import com.yalarifi.hrportal.repository.DepartmentManagerRepository;
import com.yalarifi.hrportal.repository.DepartmentRepository;
import com.yalarifi.hrportal.repository.EmployeeRepository;
import com.yalarifi.hrportal.utility.DateUtility;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTests {
	
	@Mock
	private DepartmentRepository mockDeptRepo;
	
	@Mock
	private EmployeeRepository mockEmployeeRepo;
	
	@Mock
	private DepartmentManagerRepository mockDeptManagerRepo;
	
	@InjectMocks
	private DepartmentService testee;
	
	@Test
	public void findAll_EmptyListTest() {
		when(mockDeptRepo.findAll()).thenReturn(DepartmentFactory.mockEmptyList());
		List<DepartmentDTO> expected = new ArrayList<DepartmentDTO>();
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void findAll_NullTest() {
		when(mockDeptRepo.findAll()).thenReturn(null);
		List<DepartmentDTO> expected = new ArrayList<DepartmentDTO>();
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void findAll_MultipleDepartmentsWithoutManagerTest() throws ParseException {
		List<Department> depts = DepartmentFactory.mockDepartmentList(5);
		when(mockDeptRepo.findAll()).thenReturn(depts);
		List<DepartmentDTO> expected = DepartmentDtoFactory.mockDepartmentDtoList(5);
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void findAll_SingleDepartmentWithoutManagerTest() throws ParseException {
		List<Department> depts = DepartmentFactory.mockDepartmentList(1);
		when(mockDeptRepo.findAll()).thenReturn(depts);
		List<DepartmentDTO> expected = DepartmentDtoFactory.mockDepartmentDtoList(1);
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void addDepartment_Test() throws ParseException {
		int empNo = 1;
		String deptNo = "D001";
		String deptName = "Test Department";
		
		Department dept = new Department(deptNo, deptName);
		Department toBeSaved = new Department(deptNo, deptName);
		when(mockDeptRepo.save(toBeSaved)).thenReturn(dept);
		
		Date fromDate = new Date();
		Date toDate = DateUtility.getInfinityDate();
		DepartmentEmployeeID managerId = new DepartmentEmployeeID(empNo, deptNo);

		Employee newManager = new Employee(empNo, new Date(), "first", "last", "M", new Date(), null, null, null);
		when(mockEmployeeRepo.findById(empNo)).thenReturn(Optional.of(newManager));
		
		DepartmentManager manager = new DepartmentManager(managerId, dept, newManager,fromDate, toDate);
		
		Department entity = dept;
		entity.setCurrentManager(Arrays.asList(manager));
		
		DepartmentDTO expected = new DepartmentDTO(entity);
		assertEquals(testee.addDepartment(deptNo, deptName, empNo), expected);
	}
	
	
}
