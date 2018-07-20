package com.yalarifi.hrportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.DepartmentManager;
import com.yalarifi.hrportal.entity.Employee;

@Repository
public interface DepartmentManagerRepository extends CrudRepository<DepartmentManager, Integer> {
	
	public List<DepartmentManager> findByEmployee(Employee employee);
	
}
