package com.yalarifi.hrportal.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yalarifi.hrportal.dto.DepartmentDTO;
import com.yalarifi.hrportal.loggers.LogExecutionTime;
import com.yalarifi.hrportal.service.DepartmentService;

@RestController
public class DepartmentController {
	
    private static final Logger logger = Logger.getLogger(DepartmentController.class.toString());

    
    @Autowired
    private DepartmentService departmentService;
    
    
    @RequestMapping(value = "/departments")
    @LogExecutionTime
    public List<Map<String, Object>> getAllDepartments() {
        logger.info("/GET /departments");
        
        List<Map<String, Object>> models = new ArrayList<>();
        
        List<DepartmentDTO> departments = departmentService.findAll();
        
        for( DepartmentDTO dept: departments) {
        	Map<String, Object> model = new HashMap<String, Object>();
        	model.put("name", dept.getDeptName());
        	model.put("number", dept.getDeptNumber());
        	model.put("currentManager", dept.getCurrentManager());
        	models.add(model);
        }
        return models;
    }
    
    @RequestMapping(
    		value= "/departments",
    		method= RequestMethod.POST)
    @PreAuthorize("hasAuthority('WRITE')")
    @LogExecutionTime
    public ResponseEntity<?> addDepartment(@RequestBody Map<String, Object> payload) throws ParseException {

    	if (payload.equals(null) || 
    			payload.get("name").equals(null) || 
    			payload.get("number").equals(null) || 
    			payload.get("currentManager").equals(null)) {
    		return new ResponseEntity<Error>(new Error(), HttpStatus.BAD_REQUEST);
    	}

    	

    	String deptNo = payload.get("number").toString();
    	String deptName = payload.get("name").toString();
    	int empNo = Integer.parseInt(payload.get("currentManager").toString());
    	
    	DepartmentDTO newDepartment = departmentService.addDepartment(deptNo, deptName, empNo);
    	
    	return new ResponseEntity<DepartmentDTO>(newDepartment, HttpStatus.OK);
    }
    
    @RequestMapping(
    		value= "/departments",
    		method= RequestMethod.PUT)
    @PreAuthorize("hasAuthority('WRITE')")
    @LogExecutionTime
    public ResponseEntity<?> changeManager(@RequestBody Map<String, Object> payload) throws ParseException {
    	
    	if (payload == null)
    		logger.info("payload is null");
    	
    	logger.info("/PUT /departments with deptName = " + payload.get("deptNo") + 
    							" and deptManagerId = " + payload.get("deptManagerId"));

    	
    	if (payload == null || 
    			payload.get("deptNo") == null || 
    			payload.get("deptManagerId") == null) {
    		return new ResponseEntity<Error>(new Error(), HttpStatus.BAD_REQUEST);
    	}

    	

    	String deptNo = payload.get("deptNo").toString();
    	int empNo = Integer.parseInt(payload.get("deptManagerId").toString());

    	DepartmentDTO newDepartment = departmentService.changeDepartmentManager(deptNo, empNo);
    	
    	
    	return new ResponseEntity<DepartmentDTO>(newDepartment, HttpStatus.OK);
    	
    }
}
