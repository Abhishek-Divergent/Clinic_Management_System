package com.divergent.clinicmanagementsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.divegent.doa.PatientCrudDOA;
import com.divergent.clinicmanagementsystem.dto.PatientDto;
import com.divergent.databaseconnection.JDBCConnection;

public class PatientCrud {
	public Scanner scobj = new Scanner(System.in);

	public void patientPanel() {

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
				// throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	private void patientDelete() {
		try {
			System.out.print("\n----Enter Patient ID  To Delete Patient --");
			int a = scobj.nextInt();
			PatientCrudDOA pdoa = new PatientCrudDOA(new JDBCConnection());
			pdoa.delete(a);
			System.out.println("\n-------Patient--------");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void patientUpdate() {
		try {
			PatientCrudDOA pdoa = new PatientCrudDOA(new JDBCConnection());
			patientRead();
			System.out.print("\n----Enter Patient ID Which You Want to UPDATE --");
			int rowid = scobj.nextInt();
			int p_age;
			int p_weight;
			String p_name;
			String p_gender;
			String p_address;
			String p_contact;
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
			pdoa.update(rowid, p_name, p_age, p_gender, p_contact, p_weight, p_address);
			System.out.println("\n-------Value Has Updated-------");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("\n-------Value NOT Updated-------");
		}

	}

	private void patientRead() {
		PatientCrudDOA pdoa = new PatientCrudDOA(new JDBCConnection());
		try {
			List<PatientDto> list = pdoa.read();
			System.out.printf(
					"patient Id \t Patient Name \t Patient Age \t Patient Gender \t Patient Contact \t Patient Weight \t Patient Address \n\n");
			for (PatientDto patientDto : list) {
				System.out.printf("%d %25s %10d %15s %30s  %15d %30s ", patientDto.getId(), patientDto.getName(),
						patientDto.getAge(), patientDto.getGender(), patientDto.getContact(), patientDto.getWeight(),
						patientDto.getAddress());
				System.out.println("\n");
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void patientCreate() {
		int p_id;
		int p_age;
		int p_weight;
		String p_name;
		String p_gender;
		String p_address;
		String p_contact;
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
		PatientCrudDOA pdoa = new PatientCrudDOA(new JDBCConnection());
		try {
			pdoa.create(p_id, p_name, p_age, p_gender, p_contact, p_weight, p_address);
			System.out.println("\n-------Value Has Inserted-------");
		} catch (SQLException e) {
			System.err.println(e);
			System.out.println("\n-------Value Has  Not Inserted-------");
		}

	}
}
