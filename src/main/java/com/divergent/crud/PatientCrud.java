package com.divergent.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.divergent.doa.PatientCrudDOA;
import com.divergent.dto.PatientDto;

@Component
public class PatientCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(PatientCrud.class.getName());
	private static Scanner scobj = new Scanner(System.in);
	@Autowired
	private PatientCrudDOA patientCrudDOA;
	@Autowired
	private ApplicationContext context;

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
			int i = patientCrudDOA.delete(a);
			if (i > 0) {
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
			int i = patientCrudDOA.update(rowid, p_name, p_age, p_gender, p_contact, p_weight, p_address);
			if (i > 0) {
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
			List<Map<Integer, String>> list = patientCrudDOA.read();
			if (list != null) {
				System.out.printf(
						"patient Id \t Patient Name \t Patient Age \t Patient Gender \t Patient Contact \t Patient Weight \t Patient Address \n\n");
				for (Map<Integer, String> map : list) {

					System.out.printf("%s %25s %10s %15s %30s  %15s %30s \n", map.get(1), map.get(2), map.get(3),
							map.get(4), map.get(5), map.get(6), map.get(7));
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

		PatientDto patientDto = context.getBean(PatientDto.class);
		patientDto.setId(p_id);
		patientDto.setName(p_name);
		patientDto.setWeight(p_weight);
		patientDto.setAddress(p_address);
		patientDto.setAge(p_age);
		patientDto.setGender(p_gender);
		patientDto.setContact(p_contact);
		Boolean result = PatientDto.validator(patientDto);
		if (result) {
			try {
				int i = patientCrudDOA.create(p_id, p_name, p_age, p_gender, p_contact, p_weight, p_address);
				if (i > 0) {
					myLogger.info("\n-------Value Has Inserted-------");
				} else {
					myLogger.warn("\n-------Value NOT Insert-------");
				}
			} catch (SQLException e) {
				myLogger.error(e.getMessage());
				myLogger.warn(e.getMessage());
			}
		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			patientCreate();
		}
	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Patient Crud  Opeation Panel Start : ");
		myLogger.info("  Patient  Crud  Opeation Panel Start : ");
	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Patient  Crud Opeation Panel End : ");
		myLogger.info(" Patient  Crud  Opeation Panel End : ");

	}
}
