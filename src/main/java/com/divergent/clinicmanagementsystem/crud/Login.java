package com.divergent.clinicmanagementsystem.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Login {
	@Autowired
	Doctor doctor;
	private static final Logger myLogger = LoggerFactory.getLogger(Admin.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean admin_Login() throws SQLException {
		myLogger.info("\n************************ADMIN  LOGIN PANEL************************\n");
		System.out.println("\nEnter Admin User Name  ");
		String admin_username = scobj.nextLine().trim();
		System.out.println("\nEnter Admin Password   ");
		String admin_password = scobj.nextLine().trim();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select *from admin where adminid='" + admin_username + "' and adminpassword='" + admin_password + "'");
		if (list.isEmpty()) {
			return false;
		} else {
			myLogger.warn(" Login Sucessfull : ");
			return true;
		}
	}

	public boolean doctor_Login() throws SQLException {
		String doctor_username;
		String doctor_password;
		myLogger.info("\n************************DOCTOR LOGIN PANEL************************\n");
		System.out.print("\nEnter Doctor User Name :");
		doctor_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Password :");
		doctor_password = scobj.nextLine().trim();
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select *from doctor where doc_username='"
				+ doctor_username + "' and doc_password='" + doctor_password + "'");

		if (list.isEmpty()) {
			return false;
		} else {
			myLogger.warn(" Login Sucessfull : ");
			doctor.setDoctortempusername(doctor_username);
			return true;
		}
	}
}
