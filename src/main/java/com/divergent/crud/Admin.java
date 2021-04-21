package com.divergent.crud;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.divergent.databaseconnection.JDBCConnectionInterface;

import java.sql.*;
/**
 * this class is ADMIN class  will perform login operation and ADMIN panel
 * @author JAI MAHAKAL
 *
 */
public class Admin {

	private static final Logger myLogger = LoggerFactory.getLogger(Admin.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private JDBCConnectionInterface connectionInterface;
	@Autowired
	private PatientCrud patientCrud;
	@Autowired
	private DoctorCrud crudDoctor;
	@Autowired
	private LabTestCrud labTestCrud;
	@Autowired
	private Appoinment appoinment;
	@Autowired
	private DrugsCrud drugsCrud;


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
				myLogger.info("\n----Try Again UNSUCCESSFULLY LOGIN ---\n");
				return false;
			}
		} else {
			myLogger.warn("Connection is Null ");
		}
		return false;
	}

	/**
	 * admin panel method   will perfrom all crud operation and will give option to choose
	 * 
	 * @return
	 */
	public boolean admin_pannel() {
		myLogger.info("************************ADMIN Opreation PANEL************************\n");
		adminpanel: while (true) {
			System.out.println("1. CRUD Patient " + "\n2. CRUD Doctor" + "\n3. CRUD Drugs " + "\n4. CRUD Lab Test"
					+ "\n5. Book appointment for a patient by selecting Patient, " + "\n6. Doctor and Data/time"
					+ "\n7. Exits\n\n");
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
				myLogger.warn("--- -Worng Choioce---- \n");
				continue;
			}
		}
		return true;
	}

}
