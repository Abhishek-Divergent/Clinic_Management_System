package com.divergent.clinicmanagementsystem;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.divergent.databaseconnection.JDBCConnection;

/**
 * This class contain main method of Clinic Management System Application
 * 
 * * @author JAI MAHAKAL
 *
 */
public class ClinicManagementSystem {
	private static final Logger myLogger = Logger
			.getLogger("com.divergent.clinicmanagementsystem.ClinicManagementSystem");
	public static Scanner scobj = new Scanner(System.in);

	public static void main(String[] args) {
		Admin adminobj = new Admin(new JDBCConnection());
		Doctor doctor = new Doctor(new JDBCConnection());
		myLogger.setLevel(Level.FINE);
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
						myLogger.warning("Connection is not establish");
						System.out.println("\n------Press Y Then Enter Continue------  \n");
					}
				} catch (SQLException e) {
					myLogger.fine(e.getMessage());
					myLogger.warning(e.getMessage());
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
						myLogger.warning("\n------Connection is Not Established------");
						System.out.println("\n------Press Y Then Enter Continue------  \n");
					}
				} catch (SQLException e) {
					myLogger.info("Connection is not establish");
					myLogger.fine(e.getMessage());
					myLogger.warning(e.getMessage());
					System.out.println("\n------Press Y Then Enter Continue------  \n");
				}
				scobj.next().charAt(0);
				break;
			}
			case 3:
				myLogger.info("\n------Thank you!  Application has shut down Run Again ");
				System.exit(0);
			default:

				myLogger.warning("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

}
