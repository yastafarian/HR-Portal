package com.yalarifi.hrportal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yalarifi.hrportal.dto.DepartmentDTO;
import com.yalarifi.hrportal.dto.DepartmentDtoFactory;
import com.yalarifi.hrportal.entity.Department;
import com.yalarifi.hrportal.entity.DepartmentFactory;
import com.yalarifi.hrportal.repository.DepartmentRepository;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTests {
	
	@Mock
	private DepartmentRepository mockDeptRepo;
	
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
	public void findAll_MultipleDepartmentsTest() {
		List<Department> depts = DepartmentFactory.mockDepartmentList(5);
		when(mockDeptRepo.findAll()).thenReturn(depts);
		List<DepartmentDTO> expected = DepartmentDtoFactory.mockDepartmentDtoList(5);
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void findAll_SingleDepartmentTest() {
		List<Department> depts = DepartmentFactory.mockDepartmentList(1);
		when(mockDeptRepo.findAll()).thenReturn(depts);
		List<DepartmentDTO> expected = DepartmentDtoFactory.mockDepartmentDtoList(1);
		assertEquals(testee.findAll(), expected);
	}
	
	@Test
	public void addDepartment_Test() throws ParseException {
		Department newDepartment = new Department("Test Department", "D001");
		when(mockDeptRepo.save(newDepartment)).thenReturn(newDepartment);
		DepartmentDTO departmentDTO = new DepartmentDTO(newDepartment);
		assertEquals(testee.addDepartment(departmentDTO.getDeptName(), departmentDTO.getDeptNumber(), 0), departmentDTO);
	}
	
	
}
