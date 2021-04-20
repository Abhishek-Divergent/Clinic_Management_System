package com.divergent.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.divergent.doa.DoctorCrudDOA;
import com.divergent.dto.DoctorDto;

public class DoctorCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	private DoctorCrudDOA doa;

	public void setDoa(DoctorCrudDOA doa) {
		this.doa = doa;
	}

	public void DoctorPanel() {
		p_panel: while (true) {
			myLogger.info("\n************************Doctor CRUD************************\n");
			System.out.println("1. Doctor Create ");
			System.out.println("2. Doctor Read ");
			System.out.println("3. Doctor Update ");
			System.out.println("4. Doctor Delete ");
			System.out.println("5. Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Create A doctor");
				doctorCreate();
				break;
			case 2:
				myLogger.info("Read A Doctor");
				doctorRead();
				break;
			case 3:
				myLogger.info("Update A doctor");
				doctorUpdate();
				break;
			case 4:
				myLogger.info("Delete A doctor");
				doctorDelete();
				break;
			case 5:
				break p_panel;
			default:

				myLogger.warn("--- -Worng Choioce---- \n");

				continue;
			}
		}
	}

	private void doctorDelete() {
		try {

			System.out.print("\n----Enter Doctor ID  To Delete Doctor --");
			int a = scobj.nextInt();
			int i = doa.delete(a);
			if (i == 1) {
				myLogger.info("\n----Doctor  Deleted --");
			} else {
				myLogger.info("\n----Doctor Not Deleted --");
			}

		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

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

			int i = doa.update(rowid, doc_name, doc_username, doc_password, doc_contact, doc_speciality, doc_fees);
			if (i == 1) {
				myLogger.info("\n-------Value  Updated-------");
			} else {
				myLogger.info("\n-------Value Not Updated-------");
			}

		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());
		}
	}

	private void doctorRead() {
		try {
			List<DoctorDto> list = doa.read();
			if (list != null) {
				System.out.printf(
						"doc_id          doc_username \t       doc_password \t   doc_name \t        doc_contact \t doc_speciality  doc_fees\n");
				for (DoctorDto doctorDto : list) {
					System.out.printf("%d %30s %15s  %20s %20s  %15s %10d \n", doctorDto.getId(),
							doctorDto.getUsername(), doctorDto.getName(), doctorDto.getPassword(),
							doctorDto.getContact(), doctorDto.getSpeciality(), doctorDto.getFees());
				}
			} else {
				myLogger.info("List is Null");
			}

		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());
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

		try {
			int i = doa.create(doc_id, doc_username, doc_password, doc_name, doc_contact, doc_speciality, doc_fees);
			if (i == 1) {
				myLogger.info("\n-------Value Has Inserted-------");
			} else {
				myLogger.info("\n-------Value Has Inserted-------");

			}
		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
		}
	}

}
