package com.yalarifi.hrportal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yalarifi.hrportal.entity.Privilege;
import com.yalarifi.hrportal.entity.Role;
import com.yalarifi.hrportal.entity.User;
import com.yalarifi.hrportal.repository.UserRepository;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(email + " is not a registered user!");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), 
																		user.getPassword(), 
																		user.isEnabled(), 
																		true, 
																		true, 
																		true, 
																		getAuthorities(user.getRoles()));
	}

	/*
	 * First, We’re using the Privilege – Role terms here, but in Spring, these are slightly different. 
	 * In Spring, our Privilege is referred to as Role, and also as a (granted) authority – which is 
	 * slightly confusing. Not a problem for the implementation of course, but definitely worth noting.
	 * Second – these Spring Roles (our Privileges) need a prefix; by default, that prefix is “ROLE”, but 
	 * it can be changed. We’re not using that prefix here, just to keep things simple, but keep in mind that 
	 * if you’re not explicitly changing it, it’s going to be require
	 */

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	/*
	 * Extract privileges' names from a list of Roles
	 */
	private List<String> getPrivileges(Collection<Role> roles) {
		List<String> privNames = new ArrayList<>();
		List<Privilege> privs = new ArrayList<>();
		
		for(Role role: roles) {
			privs.addAll(role.getPrivileges());
		}
		
		for(Privilege priv : privs) {
			privNames.add(priv.getName());
		}
		return privNames;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		for (String priv: privileges) {
			grantedAuths.add(new SimpleGrantedAuthority(priv));
		}
		return grantedAuths;
	}

}
