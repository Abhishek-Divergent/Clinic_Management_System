package com.divergent.clinicmanagementsystem.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * This is Doctor class Contain doctor_Logins to login doctor And Doctor Panel
 * Contain All Operation Performed By Doctor
 * 
 * @author JAI MAHAKAL
 *
 */
@Component
public class Doctor {
	private static final Logger myLogger = LoggerFactory.getLogger(Doctor.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;
	private Scanner scobj = new Scanner(System.in);
	private String doctortempusername;

	public void setDoctortempusername(String doctortempusername) {
		this.doctortempusername = doctortempusername;
	}

	public void doctor_pannel() {
		myLogger.info("Panel For Doctor To Select  Option :");
		doctorpanel: while (true) {
			System.out.print("\n1. List of Appointments  Patient \n" + "2. Add Prescription And Notes For a Patient\n"
					+ "3. Exit\n");
			System.out.print("\nEnter Choice The Option----  :");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info(" Patient List :");
				patientList();
				break;
			case 2:
				myLogger.info(" Patient Prescription :");
				addPrescription();
				break;
			case 3:

				break doctorpanel;
			default:
				myLogger.warn("----- Worng Choioce -----\n");
				continue;
			}
		}

	}

	private void addPrescription() {
		int priscription_id;
		int doc_id;
		int p_id;
		String priscription;
		String note;

		System.out.print("\nEnter Priscription Id  --:");
		priscription_id = scobj.nextInt();
		System.out.print("\nEnter Doctor Id  --:");
		doc_id = scobj.nextInt();
		System.out.print("\nEnter Patient Id  --:");
		p_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Note Name  --:");
		note = scobj.nextLine().trim();
		System.out.print("\nEnter  Priscription  --:");
		priscription = scobj.nextLine().trim();
		int i = jdbcTemplate.update("insert into  priscription  values(" + priscription_id + "," + doc_id + "," + p_id
				+ ",'" + priscription + "','" + note + "')");
		if (i > 0) {
			myLogger.info("\n:-------Value Has Inserted-------:");
		} else {
			myLogger.info("\n-------Value Has Not Inserted------- :\n");

		}
	}

	private void patientList() {
		int tempid = 0;
		Map<String, Object> map ;
		map= jdbcTemplate
				.queryForMap("SELECT doc_id FROM  doctor where doc_username='" + doctortempusername + "'");
		tempid=(int) map.get("doc_id");
		System.out.println(tempid);
		
		
		
		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = jdbcTemplate.queryForList("SELECT * FROM  appoinment where doc_id=" + tempid + "");
		myLogger.info("\n:--------- Patient Appoinment List --------\n");
		System.out.printf(
				"Appoinment_Id \t Patient_Id \t Doctor_Id \t   Doctor_Name \t Patient_Name\t \t Problem \t  Date\t  Time");
		System.out.println(list1 +" \t");

}
	

	@PostConstruct
	public void start() {
		myLogger.debug(" Doctor Panel Start  : ");
		myLogger.info(" Doctor Panel Start  : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Doctor Panel End : ");
		myLogger.info(" Doctor Panel End : ");
	}

}
