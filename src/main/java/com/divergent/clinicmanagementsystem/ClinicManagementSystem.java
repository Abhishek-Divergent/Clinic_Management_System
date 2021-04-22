package com.divergent.clinicmanagementsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.divergent.crud.Menu;
import com.divergent.javaconfiguration.javaconfig;

/**
 * This is main class Execution will here from here
 * 
 * @author Clinic Management System
 *
 */
public class ClinicManagementSystem {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	public static void main(String[] args) {
		myLogger.info(" Clinic Mangement Start: ");
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(javaconfig.class);
		Menu menu = (Menu) context.getBean(Menu.class);
		menu.showMenu();
		((AbstractApplicationContext) context).registerShutdownHook();

	}
}
