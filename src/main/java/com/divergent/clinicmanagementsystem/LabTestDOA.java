package com.divergent.clinicmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LabTestDOA {
	public void labTestPanel() {
		Scanner scobj = new Scanner(System.in);
		p_panel: while (true) {
			System.out.println("\n************************LabTest CRUD************************\n");
			System.out.println("1:LabTest Create ");
			System.out.println("2:LabTest Read ");
			System.out.println("3:LabTest Update  ");
			System.out.println("4:LabTest Delete ");
			System.out.println("5:Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				labTestCreate();
				break;
			case 2:
			        labTestRead();
				break;
			case 3:
				labTestUpdate();
				break;
			case 4:
				labTestDelete();
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

	private void labTestUpdate() {
		       Scanner scobj = new Scanner(System.in);
		       
		        labTestRead();
		    	try {System.out.println("Enter  Drugs Id Which You Want to Update");
				int rowid = scobj.nextInt();
				int labtest_id;
				String labtest_name;
				int labtest_price;

				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;

				System.out.print("\nLabTest Id----"+rowid);
				scobj.nextLine();
				System.out.print("\nEnter LabTest Name  --");
				labtest_name = scobj.nextLine().trim();
				
				System.out.print("\nEnter LabTest Price  --");
				labtest_price = scobj.nextInt();
				
			
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
							"root");
					if (connection != null) {
						statement = connection.createStatement();
						statement.executeUpdate("update labtest set labtest_name='"+labtest_name+"',labtest_price="+labtest_price+" where labtest_id="+rowid);
						System.out.println("\n-------Value Has Updated-------");
					} else
						System.err.println("\n----Connection is Null----\n");
				} catch (Exception e) {
					System.err.println(e);
				}
		
		
	}

	private void labTestRead() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				resultSet = statement.executeQuery("select *from labtest");
            System.out.printf(" LabTest Id\t  LabTest Name \t LabTest price \n");
				while (resultSet.next()) {
					System.out.printf("%5s %20s %15s" ,resultSet.getString(1), resultSet.getString(2),resultSet.getString(3) );
					System.out.println("\n");
				}
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	private void labTestCreate() {
		Scanner scobj = new Scanner(System.in);
		int labtest_id;
		String labtest_name;
		int labtest_price;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		System.out.print("\nEnter LabTest Id  --");
		labtest_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter LabTest Name  --");
		labtest_name = scobj.nextLine().trim();
		
		System.out.print("\nEnter LabTest Price  --");
		labtest_price = scobj.nextInt();
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();

				statement.executeUpdate("insert into labtest  values (" + labtest_id + ", '" + labtest_name + "','"
						+ labtest_price + "')");
				System.out.println("\n-------Value Has Inserted-------");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void labTestDelete() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scobj = new Scanner(System.in);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"root");
			if (connection != null) {
				statement = connection.createStatement();
				System.out.print("\n----Enter LABTEST ID  To Delete Drug --");
				int a = scobj.nextInt();
				int b = statement.executeUpdate("delete from LabTest where labtest_id=" + a + "");
				if (b == 0) {
					System.out.println("\n---- LabTest Not Delete----\n");
				} else
					System.out.println("\n----LabTestDelete----\n");
			} else
				System.err.println("\n----Connection is Null----\n");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	

}
