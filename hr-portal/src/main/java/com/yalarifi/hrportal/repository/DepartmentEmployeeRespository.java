package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.DepartmentEmployee;
import com.yalarifi.hrportal.entity.Employee;

@Repository
public interface DepartmentEmployeeRespository extends CrudRepository<DepartmentEmployee, Integer> {
	
	public void deleteByEmployee(Employee emp);
	
}
