package com.yalarifi.hrportal.config;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yalarifi.hrportal.entity.Privilege;
import com.yalarifi.hrportal.entity.Role;
import com.yalarifi.hrportal.entity.User;
import com.yalarifi.hrportal.repository.PrivilageRepository;
import com.yalarifi.hrportal.repository.RoleRepository;
import com.yalarifi.hrportal.repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PrivilageRepository privRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
			return;
		
		setUpRolesAndPrivileges();
	}

	private void setUpRolesAndPrivileges() {
		/*
		 * for the purpose of this demo, I am programmatically creating
		 * two users, one can read and one can write.
		 */
		
		//Create Privileges
		Privilege read = createPrivilegeIfNotFound("READ");
		Privilege write = createPrivilegeIfNotFound("WRITE");
		
		List<Privilege> adminPrivs = Arrays.asList(read, write);
		List<Privilege> userPrivs = Arrays.asList(read);
		
		//Create Roles
		Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivs);
		Role userRole = createRoleIfNotFound("ROLE_USER", userPrivs);
		
		//Create Users
		createUserIfNotFound("user", Arrays.asList(userRole));
		createUserIfNotFound("admin", Arrays.asList(adminRole));
		
		alreadySetup = true;
	}
	
	@Transactional
	private User createUserIfNotFound(String name, List<Role> roles) {
		User user = userRepo.findByEmail(name);
		
		if (user == null) {
			user = new User();
			user.setFirstName(name);
			user.setLastName(name);
			user.setEmail(name);
			user.setPassword(passwordEncoder.encode(name));
			user.setEnabled(true);
			user.setRoles(roles);
			userRepo.save(user);
		}
		
		return user;
	}
	
	@Transactional
	private Role createRoleIfNotFound(String name, List<Privilege> privs) {
		Role role = roleRepo.findByName(name);
		
		if (role == null) {
			role = new Role(name, privs);
			roleRepo.save(role);
		}
		
		return role;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {
		Privilege priv = privRepo.findByName(name);
		
		if (priv == null) {
			priv = new Privilege(name);
			privRepo.save(priv);
		}
		
		return priv;
	}
}
