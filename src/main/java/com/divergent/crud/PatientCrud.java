package com.divergent.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.divergent.doa.PatientCrudDOA;
import com.divergent.dto.PatientDto;

public class PatientCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(PatientCrud.class.getName());
	private static Scanner scobj = new Scanner(System.in);
	private PatientCrudDOA pdoa;

	public PatientCrud(PatientCrudDOA pdoa) {
		super();
		this.pdoa = pdoa;
	}

	public void patientPanel() {
		p_panel: while (true) {
			myLogger.info("\n************************Patient CRUD************************\n");
			System.out.println("1. Patient Create");
			System.out.println("2. Patient Read");
			System.out.println("3. Patient Update");
			System.out.println("4. Patient Delete");
			System.out.println("5. Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Patient Create");
				patientCreate();
				break;
			case 2:
				myLogger.info("Patient Read");
				patientRead();
				break;
			case 3:
				myLogger.info("Patient Read");
				patientUpdate();
				break;
			case 4:
				myLogger.info("Patient Delete");
				patientDelete();
				break;
			case 5:
				break p_panel;
			default:
				myLogger.warn("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	private void patientDelete() {
		try {
			myLogger.info("\n----Enter Patient ID  To Delete Patient --");
			int a = scobj.nextInt();
			int i = pdoa.delete(a);
			if (i == 1) {
				System.out.println("\n-------Patient Deleted--------");

			} else {
				System.out.println("\n-------Patient Not Deleted--------");

			}

		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());

		}

	}

	private void patientUpdate() {
		try {
			patientRead();
			myLogger.info("\n----Enter Patient ID Which You Want to UPDATE --");
			int rowid = scobj.nextInt();
			scobj.nextLine();
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
			int i = pdoa.update(rowid, p_name, p_age, p_gender, p_contact, p_weight, p_address);
			if (i == 1) {
				myLogger.info("\n-------Value Has Updated-------");
			} else {
				myLogger.warn("\n-------Value NOT Updated-------");
			}

		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

	private void patientRead() {
		try {
			List<PatientDto> list = pdoa.read();
			if (list != null) {
				System.out.printf(
						"patient Id \t Patient Name \t Patient Age \t Patient Gender \t Patient Contact \t Patient Weight \t Patient Address \n\n");
				for (PatientDto patientDto : list) {
					System.out.printf("%d %25s %10d %15s %30s  %15d %30s \n", patientDto.getId(), patientDto.getName(),
							patientDto.getAge(), patientDto.getGender(), patientDto.getContact(),
							patientDto.getWeight(), patientDto.getAddress());
				}
			} else {
				myLogger.info("List is Null");
			}

		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());
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
		try {
			int i = pdoa.create(p_id, p_name, p_age, p_gender, p_contact, p_weight, p_address);
			if (i == 1) {
				myLogger.info("\n-------Value Has Inserted-------");
			} else {
				myLogger.warn("\n-------Value NOT Insert-------");
			}
		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}
}