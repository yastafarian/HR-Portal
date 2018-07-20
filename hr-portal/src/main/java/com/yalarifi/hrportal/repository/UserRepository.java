package com.yalarifi.hrportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yalarifi.hrportal.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	public User findByEmail(String email);

}
