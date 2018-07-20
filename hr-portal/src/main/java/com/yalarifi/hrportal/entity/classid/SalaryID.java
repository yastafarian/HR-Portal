package com.yalarifi.hrportal.entity.classid;

import java.io.Serializable;
import java.util.Date;

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
public class SalaryID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1135266528416658493L;

	@Column(name="emp_no")
	private int empNo;
	
	@Column(name="from_date")
	private Date fromDate;
}
