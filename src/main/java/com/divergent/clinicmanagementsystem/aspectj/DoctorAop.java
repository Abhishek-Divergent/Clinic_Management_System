package com.divergent.clinicmanagementsystem.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DoctorAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.DoctorCrudDOA.read())")
	public void beforeRead() {
		myLogger.info("Doctor Read Started ...");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.DoctorCrudDOA.read())")
	public void afterRead() {
		myLogger.info("Doctor  Read Sucessfull ...");
	}

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.DoctorCrudDOA.create(..))")
	public void beforeCreate() {
		myLogger.info("Doctor Creating Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.DoctorCrudDOA.create(..))")
	public void afterCreate() {
		myLogger.info(" Doctor Has Created sucessfully ... ");
	}

}
