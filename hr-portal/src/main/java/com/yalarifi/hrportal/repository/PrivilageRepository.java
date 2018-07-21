package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.Privilege;

@Repository
public interface PrivilageRepository extends CrudRepository<Privilege, Integer> {

	public Privilege findByName(String name);

}
