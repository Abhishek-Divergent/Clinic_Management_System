package com.divergent.clinicmanagementsystem.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AppoinmentAop {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorAop.class.getName());

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.AppoinmentDOA.read())")
	public void beforeRead() {
		myLogger.info("Appointment Read Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.AppoinmentDOA.read())")
	public void afterRead() {
		myLogger.info("Appointment Read Sucessfull ...");
	}

	@Before("execution(* com.divergent.clinicmanagementsystem.doa.AppoinmentDOA.create())")
	public void beforeCreate() {
		myLogger.info("Appoinment Creating Started... ");
	}

	@AfterReturning("execution(* com.divergent.clinicmanagementsystem.doa.AppoinmentDOA.create())")
	public void AfteCreate() {
		myLogger.info(" Appoinment Has Created Sucessfully... ");
	}
}
