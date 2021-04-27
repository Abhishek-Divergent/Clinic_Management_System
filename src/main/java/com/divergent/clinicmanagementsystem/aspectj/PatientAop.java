package com.divergent.clinicmanagementsystem.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class PatientAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.read())")
	public void beforeRead() {
		myLogger.info("Patient Read Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.read())")
	public void afterRead() {
		myLogger.info("Patient Read Sucessfull ...");
	}

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.create())")
	public void beforeCreate() {
		myLogger.info("Patient Creating Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.create())")
	public void AfteCreate() {
		myLogger.info(" Patient Has Created Sucessfully... ");
	}


}
