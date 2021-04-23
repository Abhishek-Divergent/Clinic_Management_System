package com.divergent.crud;

import java.sql.SQLException;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is menu class this will contain Menu Option of this Application
 * 
 * @author JAI MAHAKAL
 *
 */
@Component
public class Menu {
	private static final Logger myLogger = LoggerFactory.getLogger(Menu.class.getName());
	private static Scanner scobj = new Scanner(System.in);
	@Autowired
	private Admin admin;
	@Autowired
	private Doctor doctor;

	/**
	 * showMenu Method Will show Admin login and Doctor login to choose
	 */
	public void showMenu() {
		while (true) {
			myLogger.info("**************Clinic Management System**************\n");
			System.out.println("Select Option\n");
			System.out.println("1. Admin : \n2. Doctor : \n3. Exit : ");
			int choice = 0;
			System.out.println("\nEnter Choice The Option----: ");
			choice = scobj.nextInt();
			admin: switch (choice) {
			case 1: {
				try {
					if (admin.admin_Login()) {
						admin.admin_pannel();
						break admin;
					} else {
						myLogger.warn("Connection is not Establish : ");
						myLogger.info("\n------Press Y Then Enter Continue------ \n");
					}
				} catch (SQLException e) {
					myLogger.error(e.getMessage());
					myLogger.warn(e.getMessage());
				}
				scobj.next().charAt(0);
				break;
			}
			case 2: {
				try {
					if (doctor.doctor_Login()) {
						doctor.doctor_pannel();
						break admin;
					} else {
						myLogger.warn("\n------Connection is Not Established------");
						myLogger.info("\n------Press Y Then Enter Continue------  \n");
					}
				} catch (SQLException e) {
					myLogger.error(e.getMessage());
					myLogger.warn(e.getMessage());
				}
				scobj.next().charAt(0);
				break;
			}
			case 3:
				myLogger.info("\n------Thank you!  Application Has Shut Down Run Again ");
				System.exit(0);
			default:
				myLogger.warn("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Clinic Managenment System Menu Start : ");
		myLogger.info("  Clinic Managenment System Menu Start : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Clinic Managenment System Menu End : ");
		myLogger.info(" Clinic Managenment System Menu End : ");

	}
}
