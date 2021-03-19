package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Doctor {
	String doctortempusername;
	Scanner scobj = new Scanner(System.in);
    
	public boolean doctor_Login() {
		String doctor_username;
		String doctor_password;
		System.out.println("\n************************DOCTOR PANEL************************\n");
		System.out.print("\nEnter Doctor User Name  ");;
		doctor_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Password   ");
		doctor_password = scobj.nextLine().trim();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem",
					"root", "root");
			if (connection != null) {
				Statement statement = connection.createStatement();
				ResultSet rst = statement.executeQuery("select *from doctor where doc_username='" + doctor_username
						+ "' and doc_password='" + doctor_password + "'");

				if (rst.next() == true) {
					System.out.println("\n----SUCCESSFULLY LOGIN----\n");
					doctortempusername=doctor_username;
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

	public void doctor_pannel() {
		doctorpanel:
		while (true) {

			System.out.print("\n1. List of Appointments  Patient \n" + "2. Add Prescription And Notes For a Patient\n"
					+ "3. Check Patient History and His Prescription\n"
					+ "4. Exit\n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1: 
			patientList();
			break;
			case 2:
				addPrescription();
				break;
			case 4:
				break doctorpanel;
			default:
				//throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}

		}

	}

	private void addPrescription() {
	
		Scanner scobj = new Scanner(System.in);
		int priscription_id;
		int doc_id;
		int p_id;
     	String 	priscription;
     	String note;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter priscription Id  --");
	    priscription_id = scobj.nextInt();
		System.out.print("\nEnter doctor Id  --");
	    doc_id=scobj.nextInt();
		System.out.print("\nEnter patient Id  --");
	    p_id=scobj.nextInt();
	
		scobj.nextLine();
		System.out.print("\nEnter Note Name  --");
		note = scobj.nextLine().trim();
		System.out.print("\nEnter  priscription  --");
		priscription= scobj.nextLine().trim();

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				statement.executeUpdate("insert into  priscription  values("+priscription_id+","+ doc_id +","+ p_id+",'"+priscription+"','"+ note+"')");
				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	private void patientList() {
		int tempid=0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem",
					"root", "root");
			if (connection != null) {
				Statement statement = connection.createStatement();
				 ResultSet resultSet = statement.executeQuery("SELECT doc_id FROM  doctor where doc_username='"+doctortempusername+"'");
				 while (resultSet.next()) {
					 tempid=Integer.parseInt(resultSet.getString(1));  
				}
				 resultSet=statement.executeQuery("SELECT * FROM  appoinment where doc_id="+tempid+"");
                 System.out.print("\n- -------- Patient Appoinment List--------\n ");
				System.out.printf("appoinment_id \t patient_id \t doc_id \t   doc_name \t patient_name\t \t problem \t  date\t  time");
                  while (resultSet.next()) {
					System.out.printf("\n  %s \t \t %s \t \t %s \t \t %s \t %s \t \t  %s  \t  %s \t   %s ",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5) ,resultSet.getString(6),resultSet.getString(7) ,resultSet.getString(8));
					System.out.println("\n");
                
                }
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
		System.err.println(e);
		}
	}

}
