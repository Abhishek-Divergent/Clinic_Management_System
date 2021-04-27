package com.divergent.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DoctorAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());	
	
	
	@Before("execution(* com.divergent.doa.DoctorCrudDOA.read())")
	public void beforeRead(){
		myLogger.info("Doctor before Read Start");
	}
	@After("execution(* com.divergent.crud.DoctorCrud.javadoctorRead())")
	public void afterRead(){
		myLogger.info("Doctor after Read end");
	}

}
