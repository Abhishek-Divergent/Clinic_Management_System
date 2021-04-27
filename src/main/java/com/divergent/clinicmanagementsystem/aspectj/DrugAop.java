package com.divergent.clinicmanagementsystem.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DrugAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.DrugCrudDOA.read())")
	public void beforeRead() {
		myLogger.info("Drugs Read Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.DrugCrudDOA.read())")
	public void afterRead() {
		myLogger.info("Drugs Read Sucessfull ...");
	}

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.DrugCrudDOA.create(int, String, String))")
	public void beforeCreate() {
		myLogger.info("Drugs Creating Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.DrugCrudDOA.create(int, String, String))")
	public void AfteCreate() {
		myLogger.info(" Drugs Has Created Sucessfully... ");
	}

}
