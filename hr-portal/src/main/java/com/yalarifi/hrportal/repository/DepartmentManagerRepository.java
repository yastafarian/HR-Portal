package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.DepartmentManager;

@Repository
public interface DepartmentManagerRepository extends CrudRepository<DepartmentManager, Integer> {
	
}
