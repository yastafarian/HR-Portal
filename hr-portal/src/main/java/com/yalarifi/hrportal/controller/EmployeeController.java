package com.yalarifi.hrportal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yalarifi.hrportal.dto.EmployeeDTO;
import com.yalarifi.hrportal.loggers.LogExecutionTime;
import com.yalarifi.hrportal.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	EmployeeService employeeService;
	
    @RequestMapping(
    		value= "/edit_employee",
    		method= RequestMethod.PUT)
    @LogExecutionTime
	public ResponseEntity<?> editEmployee(@RequestBody Map<String, Object> payload) throws NumberFormatException, ParseException{
    	logger.info("/PUT /employee");
    	if (payload.equals(null)) {
    		return new ResponseEntity<Error>(new Error(), HttpStatus.BAD_REQUEST);
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.s");
    	
    	EmployeeDTO employee = new EmployeeDTO(Integer.parseInt(payload.get("id").toString()),//id
    											payload.get("firstName").toString(),//first name
    											payload.get("lastName").toString(),//last name
    											formatter.parse(payload.get("birthDate").toString()),//birthday
    											payload.get("gender").toString(),//gender
    											null,//hire date
    											0,// current salary
    											"",// current title
    											null);// list of departments
    	

    	employee = employeeService.editEmployee(employee);
    	
    	return new ResponseEntity<Map<String, Object>>(employee.getModel(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee", 
			produces={"application/json"},
			method= RequestMethod.GET)
	@LogExecutionTime
	public Map<String, Object> getEmployee(@RequestParam(value = "employeeId", required=true) final String employeeId) {
		
		EmployeeDTO employee = employeeService.findByEmpNo(Integer.parseInt(employeeId));
		
		if (employee != null)
			return employee.getModel();
		else
			return null;
	}
	
}
