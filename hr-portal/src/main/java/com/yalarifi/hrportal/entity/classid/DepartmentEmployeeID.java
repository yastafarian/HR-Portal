package com.yalarifi.hrportal.entity.classid;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Embeddable
public class DepartmentEmployeeID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3828114417756093205L;

	@Column(name="emp_no")
	private int empNo;
	
	@Column(name="dept_no")
	private String deptNo;
}
