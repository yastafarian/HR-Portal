package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.DepartmentEmployee;

@Repository
public interface DepartmentEmployeeRespository extends CrudRepository<DepartmentEmployee, Integer> {
	
	
}
