package com.divergent.clinicmanagementsystem.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.doa.AppoinmentDOA;
import com.divergent.clinicmanagementsystem.dto.AppoinmentDto;

/**
 * in this is class admin will appoint a doctor to patient
 * 
 * @author JAI MAHAKAL
 *
 */
@Component
public class Appoinment {

	private static final Logger myLogger = LoggerFactory.getLogger(Appoinment.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private AppoinmentDOA appoinmentDOA;
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * appointment panel will perform all operation create ,update, delete and read
	 */

	public void appoinmentPanel() {
		myLogger.info("\n************************Appoinment CRUD************************\n");
		p_panel: while (true) {
			System.out.println("1: Appoinment Create");
			System.out.println("2: Appoinment Read");
			System.out.println("3: Appoinment Delete");
			System.out.println("4: Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Appoinment Create");
				appoinmentCreate();
				break;
			case 2:
				myLogger.info("Appoinment Read");
				appoinmentRead();
				break;

			case 3:
				myLogger.info("Appoinment Delete");

				appoinmentDelete();
				break;
			case 4:
				break p_panel;
			default:
				myLogger.warn(("--- -Worng Choioce---- \n"));
				continue;
			}

		}

	}

	/**
	 * , patient_id, ,, , , , this method will read appoinment to patient
	 */
	private void appoinmentRead() {
		try {
			List<Map<String, Object>> list = appoinmentDOA.read();
			if (list != null) {
				System.out.printf(
						"\n  appoinment_id \t patient_id \t doc_id \t patient_name \t doc_name \t problem \t date \t time \n ");
				for (Map<String, Object> map : list) {
					for (Entry<String, Object> pair : map.entrySet()) {
						System.out.printf("%10s\t", pair.getValue());
					}
					System.out.println();
				}
			} else {
				myLogger.info("List is null");
			}

		} catch (Exception e) {
			myLogger.warn(e.getMessage());
			myLogger.info(e.toString());

		}

	}

	/**
	 * this method will Delete appoinmented patient
	 * 
	 */

	private void appoinmentDelete() {

		try {
			System.out.print("\n----Enter Appoinment ID  To Delete Drug --");
			int a = scobj.nextInt();
			int i = appoinmentDOA.delete(a);
			if (i > 0) {
				myLogger.info("\n----Appoinment Delete----\n");
			} else {
				myLogger.info("\n---- Appoinment Not Delete----\n");
			}
		} catch (Exception e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());
		}

	}

	/**
	 * this method will create appoinment to patient
	 */

	private void appoinmentCreate() {
		int appoiment_id;
		int doc_id;
		int patient_id;
		String patient_name;
		String doc_name;
		String problem;
		String date;
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
		String time = scobj.nextLine();
		AppoinmentDto appoinmentDto = applicationContext.getBean(AppoinmentDto.class);
		appoinmentDto.setAppoinmentid(appoiment_id);
		appoinmentDto.setDate(date);
		appoinmentDto.setDocid(doc_id);
		appoinmentDto.setDocname(doc_name);
		appoinmentDto.setPatientid(patient_id);
		appoinmentDto.setProblem(problem);
		appoinmentDto.setTime(time);
		appoinmentDto.setPatientname(patient_name);
		Boolean result = AppoinmentDto.validator(appoinmentDto);
		if (result) {
			try {
				int i = appoinmentDOA.create(appoiment_id, doc_id, patient_id, doc_name, patient_name, problem, date,
						time);
				if (i > 0) {
					myLogger.info("\n-------Value Has Inserted-------");
				} else {
					myLogger.info("\n-------Value Has Not Inserted-------");

				}
			} catch (SQLException e) {
				myLogger.warn(e.getMessage());
				myLogger.error(e.getMessage());
			}

		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			appoinmentCreate();
		}
	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Admin Crud Opeation Panel Start : ");
		myLogger.info(" Admin Crud Opeation Panel Start : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Admin Crud Opeation Panel End : ");
		myLogger.info(" Admin Crud Opeation Panel End : ");
	}
}
