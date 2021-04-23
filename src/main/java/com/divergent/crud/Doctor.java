package com.divergent.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergent.databaseconnection.JDBCConnectionInterface;

/**
 * This is Doctor class Contain doctor_Login to login doctor And Doctor Panel
 * Contain All Operation Performed By Doctor
 * 
 * @author JAI MAHAKAL
 *
 */
@Component
public class Doctor {
	private static final Logger myLogger = LoggerFactory.getLogger(Doctor.class.getName());
	@Autowired
	private JDBCConnectionInterface connectionInterface;
	private Scanner scobj = new Scanner(System.in);
	private String doctortempusername;

	public boolean doctor_Login() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rst = null;
		String doctor_username;
		String doctor_password;
		myLogger.info("\n************************DOCTOR LOGIN PANEL************************\n");
		System.out.print("\nEnter Doctor User Name :");
		doctor_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Password :");
		doctor_password = scobj.nextLine().trim();
		connection = connectionInterface.connection();
		if (connection != null) {
			statement = connection.createStatement();
			rst = statement.executeQuery("select *from doctor where doc_username='" + doctor_username
					+ "' and doc_password='" + doctor_password + "'");

			if (rst.next() == true) {
				myLogger.info("\n----SUCCESSFULLY LOGIN----\n");
				doctortempusername = doctor_username;
				return true;
			} else {
				myLogger.info("\n----UNSUCCESSFULLY LOGIN----\n");
				myLogger.info("\n----Try Again----\n");
				return false;
			}
		} else {
			myLogger.warn("Connection is Null :");
		}
		return false;
	}

	public void doctor_pannel() {
		myLogger.info("Panel For Doctor To Select  Option :");
		doctorpanel: while (true) {
			System.out.print("\n1. List of Appointments  Patient \n" + "2. Add Prescription And Notes For a Patient\n"
					+ "3. Exit\n");
			System.out.print("\nEnter Choice The Option----  :");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info(" Patient List :");
				patientList();
				break;
			case 2:
				myLogger.info(" Patient Prescription :");
				addPrescription();
				break;
			case 3:

				break doctorpanel;
			default:
				myLogger.warn("----- Worng Choioce -----\n");
				continue;
			}
		}

	}

	private void addPrescription() {
		int priscription_id;
		int doc_id;
		int p_id;
		String priscription;
		String note;
		Connection connection = null;
		Statement statement = null;

		System.out.print("\nEnter Priscription Id  --:");
		priscription_id = scobj.nextInt();
		System.out.print("\nEnter Doctor Id  --:");
		doc_id = scobj.nextInt();
		System.out.print("\nEnter Patient Id  --:");
		p_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Note Name  --:");
		note = scobj.nextLine().trim();
		System.out.print("\nEnter  Priscription  --:");
		priscription = scobj.nextLine().trim();
		try {
			connection = connectionInterface.connection();
			statement = connection.createStatement();
			int i = statement.executeUpdate("insert into  priscription  values(" + priscription_id + "," + doc_id + ","
					+ p_id + ",'" + priscription + "','" + note + "')");
			if (i > 0) {
				myLogger.info("\n:-------Value Has Inserted-------:");
			} else {
				myLogger.info("\n-------Value Has Not Inserted------- :\n");

			}

		} catch (SQLException e) {

			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());

		}
	}

	private void patientList() {
		int tempid = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionInterface.connection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT doc_id FROM  doctor where doc_username='" + doctortempusername + "'");
			while (resultSet.next()) {
				tempid = Integer.parseInt(resultSet.getString(1));
			}
			resultSet = statement.executeQuery("SELECT * FROM  appoinment where doc_id=" + tempid + "");
			myLogger.info("\n:--------- Patient Appoinment List --------\n");
			System.out.printf(
					"Appoinment_Id \t Patient_Id \t Doctor_Id \t   Doctor_Name \t Patient_Name\t \t Problem \t  Date\t  Time");
			while (resultSet.next()) {
				System.out.printf("\n  %s \t \t %s \t \t %s \t \t %s \t %s \t \t  %s  \t  %s \t   %s  \n",
						resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
			}
		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());
		}
	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Doctor Panel Start  : ");
		myLogger.info(" Doctor Panel Start  : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Doctor Panel End : ");
		myLogger.info(" Doctor Panel End : ");
	}

}
