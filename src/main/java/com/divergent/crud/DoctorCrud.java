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

import com.divergent.doa.DoctorCrudDOA;
import com.divergent.dto.DoctorDto;

@Component
public class DoctorCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(DoctorCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private DoctorCrudDOA doctorCrudDOA;
	@Autowired
	private ApplicationContext applicationContext;

	public void DoctorPanel() {
		p_panel: while (true) {
			myLogger.info("\n************************Doctor CRUD************************\n");
			System.out.println("1. Doctor Create : ");
			System.out.println("2. Doctor Read : ");
			System.out.println("3. Doctor Update : ");
			System.out.println("4. Doctor Delete : ");
			System.out.println("5. Exit \n");
			System.out.print("\nEnter Choice The Option -------:");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Create A Doctor : ");
				doctorCreate();
				break;
			case 2:
				myLogger.info("Read A Doctor : ");
				doctorRead();
				break;
			case 3:
				myLogger.info("Update A Doctor : S");
				doctorUpdate();
				break;
			case 4:
				myLogger.info("Delete A Doctor : ");
				doctorDelete();
				break;
			case 5:
				break p_panel;
			default:

				myLogger.warn(" -----Worng Choioce----- :\n");

				continue;
			}
		}
	}

	private void doctorDelete() {
		try {

			System.out.print("\n----Enter Doctor ID  To Delete Doctor ---- : ");
			int a = scobj.nextInt();
			int i = doctorCrudDOA.delete(a);
			if (i > 0) {
				myLogger.info("\n----Doctor  Deleted ---- : ");
			} else {
				myLogger.info("\n----Doctor Not Deleted ---- : ");
			}

		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}
	}

	private void doctorUpdate() {
		doctorRead();
		System.out.print("\n----Enter Doctor ID Which You Want To UPDATE ---- : ");
		int rowid = scobj.nextInt();
		scobj.nextLine();
		String doc_username;
		String doc_password;
		String doc_name;
		String doc_contact;
		String doc_speciality;
		int doc_fees;
		System.out.print("\n\nEnter Doctor Name  --: ");
		doc_name = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Username  --: ");
		doc_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Password  --: ");
		doc_password = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Speciality  --: ");
		doc_speciality = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Contact  --: ");
		doc_contact = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Fees  --: ");
		doc_fees = scobj.nextInt();
		try {

			int i = doctorCrudDOA.update(rowid, doc_name, doc_username, doc_password, doc_contact, doc_speciality,
					doc_fees);
			if (i > 0) {
				myLogger.info("\n-------Value  Updated------- : ");
			} else {
				myLogger.info("\n-------Value Not Updated------- : ");
			}

		} catch (SQLException e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());
		}
	}

	private void doctorRead() {
		try {
			List<Map<Integer, String>> list = doctorCrudDOA.read();
			if (list != null) {
				System.out.printf(
						"Doctor_Id          Doctor_Username \t       Doctor_Password \t   Doctor_Name \t        Doctor_Contact \t Doctor_Speciality  Doctor_Fees\n");
				for (Map<Integer, String> map : list) {
					System.out.printf("%s %30s %15s  %20s %20s  %15s %10s \n", map.get(1), map.get(2), map.get(3),
							map.get(4), map.get(5), map.get(6), map.get(7));
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

		System.out.print("\nEnter Doctor Id  --: ");
		doc_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Doctor Name  --: ");
		doc_name = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Username  --: ");
		doc_username = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor password --: ");
		doc_password = scobj.nextLine().trim();

		System.out.print("\nEnter Doctor Speciality  --: ");
		doc_speciality = scobj.nextLine().trim();
		System.out.print("\nEnter Doctor Contact  --: ");
		doc_contact = scobj.nextLine().trim();
		System.out.println("\nEnter Doctor Fees  --: ");
		doc_fees = scobj.nextInt();
		DoctorDto doctorDto = applicationContext.getBean(DoctorDto.class);
		doctorDto.setId(doc_id);
		doctorDto.setName(doc_name);
		doctorDto.setPassword(doc_password);
		doctorDto.setContact(doc_contact);
		doctorDto.setFees(doc_fees);
		doctorDto.setSpeciality(doc_speciality);
		doctorDto.setUsername(doc_username);
		Boolean result = DoctorDto.validator(doctorDto);

		if (result) {

			try {
				int i = doctorCrudDOA.create(doc_id, doc_username, doc_password, doc_name, doc_contact, doc_speciality,
						doc_fees);
				if (i > 0) {
					myLogger.info("\n-------Value Has Inserted-------: ");
				} else {
					myLogger.info("\n-------Value Has Inserted------- :");

				}
			} catch (SQLException e) {
				myLogger.warn(e.getMessage());
				myLogger.error(e.getMessage());
			}
		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			doctorCreate();
		}
	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Appoinment Crud Opeation Panel Start : ");
		myLogger.info(" Appointment Crud Opeation Panel Start : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Appoinment Crud Opeation Panel End : ");
		myLogger.info(" Appointment Crud Opeation Panel End : ");

	}
}
