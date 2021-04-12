package com.divergent.clinicmanagementsystem;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.divegent.doa.AppoinmentDOA;
import com.divergent.clinicmanagementsystem.dto.AppoinmentDto;
import com.divergent.databaseconnection.JDBCConnection;

/**
 * in this is class admin will appoint a doctor to patient
 * 
 * @author JAI MAHAKAL
 *
 */
public class Appoinment {

	private static final Logger myLogger = Logger.getLogger("com.divergent.clinicmanagementsystem");
	public Scanner scobj = new Scanner(System.in);

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
				// throw new IllegalArgumentException("Unexpected value: " + choice);
				myLogger.warning(("--- -Worng Choioce---- \n"));

				continue;
			}

		}

	}

	/**
	 * , patient_id, ,, , , , this method will read appoinment to patient
	 */
	private void appoinmentRead() {
		AppoinmentDOA appoinmentDOA = new AppoinmentDOA(new JDBCConnection());
		try {
			List<AppoinmentDto> list = appoinmentDOA.read();
			System.out.printf(
					"appoinment_id        patient_id         doc_id        patient_name        doc_name        problem        date         time\n");
			for (AppoinmentDto appoinmentDto : list) {
				System.out.printf("%s\t  %20s\t   %10s\t  %10s\t  %10s\t  %10s\t  %10s\t %10s ",
						appoinmentDto.getAppoinmentid(), appoinmentDto.getPatientid(), appoinmentDto.getDocid(),
						appoinmentDto.getDocname(), appoinmentDto.getPatientname(), appoinmentDto.getProblem(),
						appoinmentDto.getDocid(), appoinmentDto.getTime());
				System.out.println("\n");
			}

		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.info(e.toString());

		}

	}

	/**
	 * this method will Delete appoinmented patient
	 * 
	 */

	private void appoinmentDelete() {
		AppoinmentDOA appoinmentDOA = new AppoinmentDOA(new JDBCConnection());
		try {
			System.out.print("\n----Enter Appoinment ID  To Delete Drug --");
			int a = scobj.nextInt();
			appoinmentDOA.delete(a);
			myLogger.info("\n----Appoinment Delete----\n");
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.info("\n---- Appoinment Not Delete----\n");
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
		AppoinmentDOA Adoa = new AppoinmentDOA(new JDBCConnection());
		try {
			Adoa.create(appoiment_id, doc_id, patient_id, doc_name, patient_name, problem, date, time);
			myLogger.info("\n-------Value Has  Inserted-------");

		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.info("\n-------Value Has NOT Inserted-------");
		}

	}

}
