package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class Admin {
	Scanner scobj = new Scanner(System.in);
	PatientDOA patientDOA = new PatientDOA();
	DoctorDOA crudDoctor=new DoctorDOA();
	DrugsDOA drugsDOA=new DrugsDOA();
	LabTestDOA labTestDOA=new LabTestDOA();
	Appoinment appoinment=new Appoinment();
/** this method will connect  to the database to verify  Admin 
 * method will verify Admin password and username 
 * Method will return Boolean Value
 * @return  
 */
	 protected boolean admin_Login() {
		String admin_username;
		String admin_password;
		System.out.println("************************ADMIN PANEL************************\n");
		System.out.print("\nEnter Admin User Name  ");
		admin_username = scobj.nextLine().trim();
		System.out.print("\nEnter Admin Password   ");
		admin_password = scobj.nextLine().trim();

		try {
		
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem",
					"root", "root");
			if (connection != null) {
				Statement statement = connection.createStatement();
				ResultSet rst = statement.executeQuery("select *from admin where adminid='" + admin_username
						+ "' and adminpassword='" + admin_password + "'");
				if (rst.next() == true) {
					System.out.println("\n----SUCCESSFULLY LOGIN----\n");
					return true;
				} else {
					System.out.println("\n----Try Again----\n");
					return false;
				}

			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		return false;
	}
/**
 *  this method take input to perform admin operation
 * @return
 */
	public boolean admin_pannel() {
		adminpanel: while (true) {
			System.out.println("************************ADMIN PANEL************************\n");
			System.out.println("1. CRUD Patient " + "\n2. CRUD Doctor" + "\n3. CRUD Drugs " + "\n4. CRUD Lab Test"
					+ "\n5. Book appointment for a patient by selecting Patient, " + "\n6. Doctor and Data/time"
					+ "\n7. Exits\n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1: {
				patientDOA.patientPanel();
				break;
			}
			case 2:
				 crudDoctor.DoctorPanel();
				 break;
			case 3:
				 drugsDOA.DrugsPanel();
				 break;
			case 4:
				labTestDOA.labTestPanel();break;
			case 5:
				appoinment.appoinmentPanel();break;
			case 7:
				break adminpanel;
			default:
				//throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}
		return true;	}
  
}
