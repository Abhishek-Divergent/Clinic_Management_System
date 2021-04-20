package com.divergent.clinicmanagementsystem;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.crud.Menu;
import org.slf4j.LoggerFactory;

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
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		Menu menu = (Menu) context.getBean("menuid");
		menu.showMenu();

	}
}
