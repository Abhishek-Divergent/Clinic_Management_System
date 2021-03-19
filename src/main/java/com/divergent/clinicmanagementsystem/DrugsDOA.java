package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DrugsDOA {

	public void DrugsPanel() {
		Scanner scobj = new Scanner(System.in);
		p_panel: while (true) {
			System.out.println("\n************************Drugs CRUD************************\n");
			System.out.println("1: Drugs Create");
			System.out.println("2: Drugs Read");
			System.out.println("3: Drugs Update");
			System.out.println("4: Drugs Delete");
			System.out.println("5: Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				DrugsCreate();
				break;
			case 2:
				DrugsRead();
				break;
			case 3:
				DrugsUpdate();
				break;
			case 4:
				DrugsDelete();
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

	private void DrugsUpdate() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement.executeQuery("select *from drugs");

				DrugsRead();
				System.out.print("\n----Enter DrugsID Which You Want to UPDATE --");
				int rowid = scobj.nextInt();
				updatedrugsData(rowid);
			} else
				System.err.println("\nConnection is Null\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void updatedrugsData(int rowid) {
		Scanner scobj = new Scanner(System.in);
		int Drugs_id;
		String Drugs_name;
		String Drugs_description;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nDrug Id Is----" + rowid+"----");

		System.out.print("\nEnter Drug Name --");
		Drugs_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Drugs Description  --");
		Drugs_description = scobj.nextLine().trim();

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				statement.executeUpdate("update drugs set drugs_name='" + Drugs_name + "',drugs_description='"
						+ Drugs_description + "' where drugs_id=" + rowid);
				System.out.println("\n-------Value Has Updated-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void DrugsRead() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select *from drugs");
				while (resultSet.next()) {
					System.out.printf("%s\t %s\t %s ",resultSet.getString(1) ,resultSet.getString(2),resultSet.getString(3));
					System.out.println("\n");
				}
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void DrugsCreate() {
		Scanner scobj = new Scanner(System.in);
		int Drugs_id;
		String Drugs_name;
		String Drugs_description;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter Drug Id  --");
		Drugs_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Drug Name  --");
		Drugs_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Drugs_description  --");
		Drugs_description = scobj.nextLine().trim();

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("insert into drugs  values (" + Drugs_id + ", '" + Drugs_name + "','"
						+ Drugs_description + "')");
				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void DrugsDelete() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				System.out.print("\n----Enter Drug ID  To Delete Drug --");
				int a = scobj.nextInt();
				int b = statement.executeUpdate("delete from drugs where drugs_id=" + a + "");
				if (b == 0) {
					System.out.println("\n---- Drug  Not Delete----\n");
				} else
					System.out.println("\n---- Drug Delete----\n");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
