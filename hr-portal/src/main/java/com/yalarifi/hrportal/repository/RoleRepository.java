package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	public Role findByName(String name);

}
