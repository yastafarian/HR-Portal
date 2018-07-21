package com.yalarifi.hrportal.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {
	
	public Role(String name, List<Privilege> privs) {
		this.name = name;
		this.privileges = privs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;
	
	@ManyToMany
	@JoinTable(
			name = "roles_privileges", 
			joinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(
					name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;
}
