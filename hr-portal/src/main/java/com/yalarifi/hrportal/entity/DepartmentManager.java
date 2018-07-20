package com.yalarifi.hrportal.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yalarifi.hrportal.entity.classid.DepartmentEmployeeID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="dept_manager")
@SuppressWarnings("unused")
public class DepartmentManager {
	
	public DepartmentManager(DepartmentEmployeeID departmentEmployeeID, Date fromDate, Date toDate) {
		this.id = departmentEmployeeID;
		this.from_date = fromDate;
		this.to_date = toDate;
	}

	@EmbeddedId
	private DepartmentEmployeeID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("deptNo")
	@JoinColumn(name="dept_no")
	private Department department;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("empNo")
	@JoinColumn(name="emp_no")
	private Employee employee;
	
	private Date from_date;
	
	private Date to_date;
}