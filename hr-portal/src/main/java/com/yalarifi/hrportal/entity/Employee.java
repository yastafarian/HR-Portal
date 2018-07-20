package com.yalarifi.hrportal.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="employees")
@SuppressWarnings("unused")
public class Employee {
	
	@Id
	@Column(name="emp_no")
	private int empNo;
	
	Date birth_date;
	
	String first_name;
	
	String last_name;
	
	String gender;
	
	Date hire_date;
	
	@OneToMany(
			mappedBy="empNo",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	@Where(clause="to_date='9999-01-01'")
	List<Title> titles;
	
	@OneToMany(
			mappedBy="empNo",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	@Where(clause="to_date='9999-01-01'")
	List<Salary> salaries;
	
	@OneToMany(
			mappedBy="employee",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	List<DepartmentEmployee> departments;
	
}
