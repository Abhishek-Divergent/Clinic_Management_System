package com.divergent.clinicmanagementsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.divegent.doa.DoctorCrudDOA;
import com.divergent.clinicmanagementsystem.dto.DoctorDto;
import com.divergent.databaseconnection.JDBCConnection;

public class DoctorCrud {
	public Scanner scobj = new Scanner(System.in);

	public void DoctorPanel() {
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
				// throw new IllegalArgumentException("Unexpected value: " + choice);
				System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	private void doctorDelete() {
		try {
			DoctorCrudDOA doa = new DoctorCrudDOA(new JDBCConnection());
			System.out.print("\n----Enter Doctor ID  To Delete Doctor --");
			int a = scobj.nextInt();
			doa.delete(a);
			System.out.print("\n----Doctor  Deleted --");
		} catch (SQLException e) {
			System.err.println(e);
			System.out.print("\n----Doctor Not Deleted --");
		}
	}

	private void doctorUpdate() {
		doctorRead();
		System.out.print("\n----Enter Doctor ID Which You Want to UPDATE --");
		int rowid = scobj.nextInt();
		scobj.nextLine();
		String doc_username;
		String doc_password;
		String doc_name;
		String doc_contact;
		String doc_speciality;
		int doc_fees;
		System.out.print("\n\nEnter Doctor Name  --");
		doc_name = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Username  --");
		doc_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor password  --");
		doc_password = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Speciality  --");
		doc_speciality = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Contact  --");
		doc_contact = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor fees  --");
		doc_fees = scobj.nextInt();
		try {
			DoctorCrudDOA doa = new DoctorCrudDOA(new JDBCConnection());
			doa.update(rowid, doc_name, doc_username, doc_password, doc_contact, doc_speciality, doc_fees);
			System.out.println("\n-------Value  Updated-------");
		} catch (SQLException e) {
			System.out.println("\n-------Value Not Updated-------");

		}
	}

	private void doctorRead() {
		try {
			DoctorCrudDOA doa = new DoctorCrudDOA(new JDBCConnection());
			List<DoctorDto> dtos = doa.read();
			System.out.printf(
					"doc_id          doc_username \t       doc_password \t   doc_name \t        doc_contact \t doc_speciality  doc_fees\n");
			for (DoctorDto doctorDto : dtos) {
				System.out.printf("%d %30s %15s  %20s %20s  %15s %10d ", doctorDto.getId(), doctorDto.getUsername(),
						doctorDto.getName(), doctorDto.getPassword(), doctorDto.getContact(), doctorDto.getSpeciality(),
						doctorDto.getFees());
				System.out.println("\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	private void doctorCreate() {

		int doc_id;
		String doc_username;
		String doc_password;
		String doc_name;
		String doc_contact;
		String doc_speciality;
		int doc_fees;
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
		System.out.println("\nEnter Doctor fees  --");
		doc_fees = scobj.nextInt();

		DoctorCrudDOA doa = new DoctorCrudDOA(new JDBCConnection());
		try {
			doa.create(doc_id, doc_username, doc_password, doc_name, doc_contact, doc_speciality, doc_fees);
			System.out.println("\n-------Value Has Inserted-------");
		} catch (SQLException e) {
			System.err.println(e);
			System.out.println("\n-------Value Has  Not Inserted-------");
		}
	}

}
