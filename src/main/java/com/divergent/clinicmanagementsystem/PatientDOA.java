package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PatientDOA {

	public void patientPanel() {
		Scanner scobj = new Scanner(System.in);
		p_panel: while (true) {
			System.out.println("\n************************Patient CRUD************************\n");
			System.out.println("1. Patient Create");
			System.out.println("2. Patient Read");
			System.out.println("3. Patient Update");
			System.out.println("4. Patient Delete");
			System.out.println("5. Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				patientCreate();
				break;
			case 2:
				patientRead();
				break;
			case 3:
				patientUpdate();
				break;
			case 4:
				patientDelete();
				break;
			case 5:
				break p_panel;
			default:
				//throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	private void patientDelete() {
		Connection connection = null;
		Statement statement = null;
		Scanner scobj = new Scanner(System.in);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				System.out.print("\n----Enter Patient ID  To Delete Patient --");
				int a = scobj.nextInt();
				int b = statement.executeUpdate("delete from patient where p_id=" + a + "");
				if (b == 0) {
					System.out.println("\n----Patient Not Delete----\n");
				} else
					System.out.println("\n----Patient Delete----\n");

			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void patientUpdate() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from patient ");
				patientRead();
				System.out.print("\n----Enter Patient ID Which You Want to UPDATE --");
				int rowid = scobj.nextInt();
				updatePatientData(rowid);

			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void updatePatientData(int id) {
		Scanner scobj = new Scanner(System.in);
		int p_id;
		int p_age;
		int p_weight;
		String p_name;
		String p_gender;
		String p_address;
		String p_contact;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		System.out.print("\nEnter Patient Name  --");
		p_name = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Gender  --");
		p_gender = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Age  --");
		p_age = scobj.nextInt();
		System.out.print("\nEnter Patient Weight  --");
		p_weight = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Patient Address  --");
		p_address = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Contact  --");
		p_contact = scobj.nextLine().trim();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("update patient set p_name='" + p_name + "', p_age=" + p_age + ",p_gender ='"
						+ p_gender + "',p_contact='" + p_contact + "',p_weight=" + p_weight + ",p_address='" + p_address
						+ "' where p_id=" + id);
				System.out.println("\n-------Value Has Updated-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void patientRead() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement.executeQuery("select * from patient ");
				System.out.printf("patient Id \t Patient Name \t Patient Age \t Patient Gender \t Patient Contact \t Patient Weight \t Patient Address \n\n");
				while (resultSet.next()) {
					System.out.printf("%d %25s %10d %15s %30s  %15d %0s ",resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5) ,resultSet.getInt(6),resultSet.getString(7) );
					System.out.println("\n");
				}
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void patientCreate() {
		Scanner scobj = new Scanner(System.in);
		int p_id;
		int p_age;
		int p_weight;
		String p_name;
		String p_gender;
		String p_address;
		String p_contact;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter Patient Id  --");
		p_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Patient Name  --");
		p_name = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Gender  --");
		p_gender = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Age  --");
		p_age = scobj.nextInt();
		System.out.print("\nEnter Patient Weight  --");
		p_weight = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Patient Address  --");
		p_address = scobj.nextLine().trim();
		System.out.print("\nEnter Patient Contact  --");
		p_contact = scobj.nextLine().trim();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("insert into patient values (" + p_id + ", '" + p_name + "'," + p_age + ", '"
						+ p_gender + "', '" + p_contact + "', " + p_weight + ", '" + p_address + "')");

				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
