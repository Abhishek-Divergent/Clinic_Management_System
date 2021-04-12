package com.divergent.clinicmanagementsystem;

import java.util.Scanner;

import java.util.logging.Logger;

import com.divergent.databaseconnection.JDBCConnectionInterface;

import java.sql.*;

public class Admin {

	private static final Logger myLogger = Logger.getLogger("com.divergent.clinicmanagementsystem");
	JDBCConnectionInterface connectionInterface;

	public Admin(JDBCConnectionInterface connectionInterface) {
		this.connectionInterface = connectionInterface;
	}

	public Scanner scobj = new Scanner(System.in);
	PatientCrud patientCrud = new PatientCrud();
	DoctorCrud crudDoctor = new DoctorCrud();
	DrugsCrud drugsCrud = new DrugsCrud();
	LabTestCrud labTestCrud = new LabTestCrud();
	Appoinment appoinment = new Appoinment();

	/**
	 * this method will connect to the database to verify Admin method will verify
	 * Admin password and username Method will return Boolean Value
	 * 
	 * @return
	 */
	public boolean admin_Login() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rst = null;
		myLogger.info("\n************************ADMIN  LogIn PANEL************************\n");
		System.out.println("\nEnter Admin User Name  ");
		String admin_username = scobj.nextLine().trim();
		System.out.println("\nEnter Admin Password   ");
		String admin_password = scobj.nextLine().trim();
		connection = connectionInterface.connection();
		if (connection != null) {
			statement = connection.createStatement();
			rst = statement.executeQuery("select *from admin where adminid='" + admin_username + "' and adminpassword='"
					+ admin_password + "'");
			if (rst.next() == true) {
				myLogger.info("\n----SUCCESSFULLY LOGIN----\n");
				return true;
			} else {
				myLogger.warning("\n----Try Again UNSUCCESSFULLY LOGIN ---\n");
				return false;
			}
		} else {
			myLogger.warning("Connection is Null ");
		}
		return false;
	}

	/**
	 * this method take input to perform admin operation
	 * 
	 * @return
	 */
	public boolean admin_pannel() {
		myLogger.info("************************ADMIN Opreation PANEL************************\n");
		adminpanel: while (true) {
			System.out.println("1. CRUD Patient " + "\n2. CRUD Doctor" + "\n3. CRUD Drugs " + "\n4. CRUD Lab Test"
					+ "\n5. Book appointment for a patient by selecting Patient, " + "\n6. Doctor and Data/time"
					+ "\n7. Exits\n");
			System.out.println();
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1: {
				myLogger.info("Patient Crud Operation");
				patientCrud.patientPanel();
				break;
			}
			case 2:
				myLogger.info("Doctor Crud Operation");
				crudDoctor.DoctorPanel();
				break;
			case 3:
				myLogger.info("Drugs Crud Operation");
				drugsCrud.DrugsPanel();
				break;
			case 4:
				myLogger.info("Labtest Crud Operation");
				labTestCrud.labTestPanel();
				break;
			case 5:
				myLogger.info("Appointment Operation");

				appoinment.appoinmentPanel();
				break;
			case 7:
				break adminpanel;
			default:
				myLogger.warning("--- -Worng Choioce---- \n");
				continue;
			}
		}
		return true;
	}

}
