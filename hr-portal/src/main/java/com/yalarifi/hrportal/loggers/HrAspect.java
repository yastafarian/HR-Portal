package com.yalarifi.hrportal.loggers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HrAspect {
	
	private static final Logger logger = Logger.getLogger(HrAspect.class);
	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		
		Object proceed =  joinPoint.proceed();
		
		long executionTime = System.currentTimeMillis() - start;
		
		logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}
	
	

}
