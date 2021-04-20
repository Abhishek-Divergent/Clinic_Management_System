package com.divergent.crud;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu {
	private static final Logger myLogger = LoggerFactory.getLogger(Menu.class.getName());
	private static Scanner scobj = new Scanner(System.in);
	private Admin adminobj;
	private Doctor doctor;

	public void showMenu() {
		// myLogger.Level(Level.INFO);
		while (true) {
			myLogger.info("**************Clinic Management System**************\n");
			System.out.println("Select Option\n");
			System.out.println("1. Admin\n2. Doctor\n3. Exit");
			int choice = 0;
			System.out.println("\nEnter Choice The Option----  ");
			choice = scobj.nextInt();
			admin: switch (choice) { 
			case 1: {
				try {
					if (adminobj.admin_Login()) {
						adminobj.admin_pannel();
						break admin;
					} else {
						myLogger.warn("Connection is not establish");
						System.out.println("\n------Press Y Then Enter Continue------  \n");
					}
				} catch (SQLException e) {
					myLogger.error(e.getMessage());
					myLogger.warn(e.getMessage());
					myLogger.info("Connection is not establish");
					System.out.println("\n------Press Y Then Enter Continue------  \n");
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
						System.out.println("\n------Press Y Then Enter Continue------  \n");
					}
				} catch (SQLException e) {
					myLogger.error(e.getMessage());
					myLogger.warn(e.getMessage());
					myLogger.info("Connection is not establish");
					System.out.println("\n------Press Y Then Enter Continue------  \n");
				}
				scobj.next().charAt(0);
				break;
			}
			case 3:
				myLogger.info("\n------Thank you!  Application has shut down Run Again ");
				System.exit(0);
			default:

				myLogger.warn("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	public Admin getAdminobj() {
		return adminobj;
	}

	public void setAdminobj(Admin adminobj) {
		this.adminobj = adminobj;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
