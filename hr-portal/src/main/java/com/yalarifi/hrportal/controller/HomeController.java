package com.yalarifi.hrportal.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	
	@RequestMapping("/")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("content", "Welcome to HR Portal!");
		return model;
	}
	
	/*
	 * If this route is reachable then it will return the currently
	 * authenticated user, otherwise Spring Security will respond 
	 * with 401 code through an AuthenticationEntryPoint
	 */
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		logger.info("/user called " + user.getName());
		return user;
	}
}
