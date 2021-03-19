    package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DoctorDOA {

	public void DoctorPanel() {
		Scanner scobj = new Scanner(System.in);
		p_panel: while (true) {
			System.out.println("\n************************Doctor CRUD************************\n");
			System.out.println("1. Doctor Create ");
			System.out.println("2. Doctor Read ");
			System.out.println("3. Doctor Update ");
			System.out.println("4. Doctor Delete ");
			System.out.println("5. Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				doctorCreate();
				break;
			case 2:
				doctorRead();
				break;
			case 3:
				doctorUpdate();
				break;
			case 4:
				doctorDelete();
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

	private void doctorDelete() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				System.out.print("\n----Enter Doctor ID  To Delete Patient --");
				int a = scobj.nextInt();
				int b = statement.executeUpdate("delete from doctor where doc_id=" + a + "");
				if (b == 0) {
					System.out.println("\n----Doctor Not Delete----\n");
				} else
					System.out.println("\n----Doctor  Delete----\n");

			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void doctorUpdate() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				doctorRead();
				System.out.print("\n----Enter Doctor ID Which You Want to UPDATE --");
				int rowid = scobj.nextInt();
				updateDoctorData(rowid);

			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void updateDoctorData(int rowid) {
		Scanner scobj = new Scanner(System.in);
		String doc_username;
		String doc_password;
		String doc_name;
		String doc_contact;
		String doc_speciality;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter Doctor Name  --");
		doc_name = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Username  --");
		doc_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor password  --");
		doc_password = scobj.nextLine().trim();

		System.out.print("\nEnter Doctor Speciality  --");
		doc_speciality = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Contact  --");
		doc_contact = scobj.nextLine().trim();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				statement.executeUpdate("update doctor set doc_name='"+doc_name+"',doc_username='"+doc_username+"',doc_password='"+doc_password+"',doc_contact='"+doc_contact+"',doc_speciality='"+doc_speciality+"' where doc_id = " + rowid);
				System.out.println("\n-------Value Has Updated-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void doctorRead() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement.executeQuery("select * from doctor");
				System.out.printf("doc_id          doc_username \t       doc_password \t   doc_name \t        doc_contact \t doc_speciality  doc_fees\n");
				while (resultSet.next()) {
				
					System.out.printf("%d %30s %15s  %20s %20s  %15s %10d ", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
					System.out.println("\n");
				}
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void doctorCreate() {
		Scanner scobj = new Scanner(System.in);
		int doc_id;
		String doc_username;
		String doc_password;
		String doc_name;
		String doc_contact;
		String doc_speciality;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter Doctor Id  --");
		doc_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Doctor Name  --");
		doc_name = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Username  --");
		doc_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor password --");
		doc_password = scobj.nextLine().trim();

		System.out.print("\nEnter Doctor Speciality  --");
		doc_speciality = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Contact  --");
		doc_contact = scobj.nextLine().trim();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("insert into doctor values (" + doc_id + ", '" + doc_username + "','"
						+ doc_password + "', '" + doc_name + "', '" + doc_contact + "','" + doc_speciality + "')");
				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
