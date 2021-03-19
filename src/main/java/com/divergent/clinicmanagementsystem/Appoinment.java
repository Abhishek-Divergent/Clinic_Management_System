package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Appoinment {
	public void appoinmentPanel() {
		@SuppressWarnings("resource")
		Scanner scobj = new Scanner(System.in);
		p_panel: while (true) {
		
			System.out.println("\n************************Appoinment CRUD************************\n");
			System.out.println("1: Appoinment Create");
     	    System.out.println("2: Appoinment Read");
//		System.out.println("3:LabTest Update\n");
         	System.out.println("4: Appoinment Delete");
			System.out.println("5: Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:

				appoinmentCreate();
				break;
			case 2:
				 appoinmentRead();
				break;
			case 3:
				// labTestUpdate();
				break;
			case 4:
				appoinmentDelete();
				break;
			case 5:
				break p_panel;
			default:
				//throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
			scobj.close();
		}

	}

	private void appoinmentRead() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement.executeQuery("select *from appoinment");

				while (resultSet.next()) {
					System.out.printf("%s\t  %s\t   %s\t  %s\t  %s\t  %s\t  %s\t %s ", resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));
					System.out.println("\n");
				}
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	private void appoinmentDelete() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				System.out.print("\n----Enter Appoinment ID  To Delete Drug --");
				int a = scobj.nextInt();
				int b = statement.executeUpdate("delete from  appoinment where appoinment_id=" + a + "");
				if (b == 0) {
					System.out.println("\n---- Appoinment Not Delete----\n");
				} else
					System.out.println("\n----Appoinment Delete----\n");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	

	private void appoinmentCreate() {
		Scanner scobj = new Scanner(System.in);
		int appoiment_id;
		int doc_id;
		int patient_id;
		String patient_name;
		String doc_name;
		String problem;
		String date;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter  Appoinment Id --");
		appoiment_id = scobj.nextInt();

		System.out.print("\nEnter  Doctor Id  --");
		doc_id = scobj.nextInt();

		System.out.print("\nEnter  Patient Id  --");
		patient_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter  Doctor Name  --");
		doc_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Patient Name  --");
		patient_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Problem  --");
		problem = scobj.nextLine().trim();

		System.out.print("\nEnter  Date  --");
		date = scobj.nextLine();
		System.out.print("\nEnter  Time  --");
		String time= scobj.nextLine();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("insert into appoinment values( "+appoiment_id+","+doc_id+","+patient_id+", '"+doc_name+"','"+ patient_name+"','"+problem+"','"+date+"','"+time+"')");
				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
