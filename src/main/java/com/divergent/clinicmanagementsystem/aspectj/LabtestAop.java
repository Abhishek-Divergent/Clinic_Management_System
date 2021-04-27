package com.divergent.clinicmanagementsystem.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LabtestAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.LabTestCrudDOA.read())")
	public void beforeRead() {
		myLogger.info("Labtest Read Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.LabTestCrudDOA.read())")
	public void afterRead() {
		myLogger.info("Labtest Read Sucessfull ...");
	}

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.create(..))")
	public void beforeCreate() {
		myLogger.info("Labtest Creating Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.PatientCrudDOA.create(..))")
	public void AfteCreate() {
		myLogger.info(" Labtest Has Created Sucessfully... ");
	}


}
