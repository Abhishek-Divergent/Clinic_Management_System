package com.divergent.clinicmanagementsystem;

import java.util.Scanner;

/**
 * This class contain main method of Clinic Management System Application
 * 
 * * @author JAI MAHAKAL
 *
 */
public class ClinicManagementSystem {
	public static void main(String[] args) {
		Admin adminobj = new Admin();
		Doctor doctor = new Doctor();
		Scanner scobj = new Scanner(System.in);
		while (true) {
			System.out.println("**************Clinic Management System**************\n");
			System.out.println("Select Option\n");
			System.out.println("1. Admin\n2. Doctor\n3. Exit");
			int choice=0; 
			System.out.print("\nEnter Choice The Option----  ");
				 choice = scobj.nextInt();
			
			
			admin: switch (choice) {
			case 1: {
				if (adminobj.admin_Login()) {
					adminobj.admin_pannel();
					break admin;
				} else {
					System.out.println("\n------Connection is Not Established------");
					System.out.print("\n------Press Y Then Enter Continue------  \n");
				}
				scobj.next().charAt(0);
				break;
			}
			case 2: {
				if (doctor.doctor_Login()) {
					doctor.doctor_pannel();
					break admin;
				} else {
					System.out.println("\n------Connection is Not Established------");
					System.out.print("\n------Press Y Then Enter Continue------\n");
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
		}

	}

}
