package com.divergent.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divergent.databaseconnection.JDBCConnectionInterface;

@Component
public class Login {
	@Autowired
	Doctor doctor;
	private static final Logger myLogger = LoggerFactory.getLogger(Admin.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private JDBCConnectionInterface connectionInterface;

	public boolean admin_Login() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rst = null;
		myLogger.info("\n************************ADMIN  LOGIN PANEL************************\n");
		System.out.println("\nEnter Admin User Name  ");
		String admin_username = scobj.nextLine().trim();
		System.out.println("\nEnter Admin Password   ");
		String admin_password = scobj.nextLine().trim();
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		rst = statement.executeQuery(
				"select *from admin where adminid='" + admin_username + "' and adminpassword='" + admin_password + "'");
		if (rst.next() == true) {
			myLogger.info("\n----SUCCESSFULLY LOGIN----\n");
			return true;
		} else {
			myLogger.info("\n----UNSUCCESSFULLY LOGIN ---\n");
		}
		return false;
	}

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
		statement = connection.createStatement();
		rst = statement.executeQuery("select *from doctor where doc_username='" + doctor_username
				+ "' and doc_password='" + doctor_password + "'");

		if (rst.next() == true) {
			myLogger.info("\n----SUCCESSFULLY LOGIN----\n");
			doctor.setDoctortempusername(doctor_username);
			return true;
		} else {
			myLogger.info("\n----UNSUCCESSFULLY LOGIN----\n");
		}

		return false;
	}
}
