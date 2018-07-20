package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
	
}
