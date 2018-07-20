package com.yalarifi.hrportal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="departments")
@SuppressWarnings("unused")
public class Department {
	
	public Department(String deptNumber, String deptName, List<DepartmentManager> currentManager) {
		this.dept_no = deptNumber;
		this.dept_name = deptName;
		this.currentManager = currentManager;
	}

	public Department(String deptNo, String deptName) {
		this.dept_no = deptNo;
		this.dept_name = deptName;
	}

	@Id
	private String dept_no;
	
	private String dept_name;
	
	@OneToMany(fetch = FetchType.LAZY, 
				mappedBy="department",
				cascade = CascadeType.ALL, 
		        orphanRemoval = true)
	private List<DepartmentEmployee> employee;
	
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy="department",
			cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	@Where(clause="to_date='9999-01-01'")
	private List<DepartmentManager> currentManager;

}
