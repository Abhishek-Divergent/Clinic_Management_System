package com.divergent.clinicmanagementsystem.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.divergent.clinicmanagementsystem.crud.Menu;
import com.divergent.clinicmanagementsystem.javaconfiguration.JavaConfig;

/**
 * This is main class of Application
 * 
 * @author Clinic Management System
 *
 */

public class ClinicManagementSystem {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	public static void main(String[] args) {
		myLogger.info(" Clinic Mangement Start: ");
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev");
		context.register(JavaConfig.class);
		context.refresh();
		Menu menu = (Menu) context.getBean(Menu.class);
		menu.showMenu();
		((AbstractApplicationContext) context).registerShutdownHook();

		System.exit(0);
	}
}
