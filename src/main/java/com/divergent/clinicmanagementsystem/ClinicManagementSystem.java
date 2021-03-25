package com.divergent.clinicmanagementsystem;

import java.sql.SQLException;
import java.util.Scanner;

import com.divergent.databaseconnection.JDBCConnection;

/**
 * This class contain main method of Clinic Management System Application
 * 
 * * @author JAI MAHAKAL
 *
 */
public class ClinicManagementSystem {
	public static Scanner scobj = new Scanner(System.in);
	public static void main(String[] args) {
		Admin adminobj = new Admin(new JDBCConnection());
		Doctor doctor = new Doctor(new JDBCConnection());
		
		while (true) {
			System.out.println("**************Clinic Management System**************\n");
			System.out.println("Select Option\n");
			System.out.println("1. Admin\n2. Doctor\n3. Exit");
			int choice=0; 
			System.out.print("\nEnter Choice The Option----  ");
				 choice = scobj.nextInt();
			admin: switch (choice) {
			case 1: {
				try {
					if (adminobj.admin_Login()) {
						adminobj.admin_pannel();
						break admin;
					} else {
						System.out.println("\n------Connection is Not Established------");
						System.out.print("\n------Press Y Then Enter Continue------  \n");
						
					}
				} catch (SQLException e) {
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
						System.out.println("\n------Connection is Not Established------");
						System.out.print("\n------Press Y Then Enter Continue------\n");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scobj.next().charAt(0);
				break;
			}
			case 3:
				System.out.println("\n------Thank you!  Application has shut down Run Again ");
				System.exit(0);
			default:
				//throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;	
			}
	}}

}
