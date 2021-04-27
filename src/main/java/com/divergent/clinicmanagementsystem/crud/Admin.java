package com.divergent.clinicmanagementsystem.crud;

import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * this class is ADMIN class will perform login operation and ADMIN panel
 * contain admin menu
 * 
 * @author JAI MAHAKAL
 *
 */
@Component
public class Admin {

	private static final Logger myLogger = LoggerFactory.getLogger(Admin.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private PatientCrud patientCrud;
	@Autowired
	private DoctorCrud crudDoctor;
	@Autowired
	private LabTestCrud labTestCrud;
	@Autowired
	private Appoinment appoinment;
	@Autowired
	private DrugsCrud drugsCrud;

	/**
	 * ADMIN Panel method will Perform All CRUD Operation And Will Give Option to
	 * choose
	 * 
	 * @return
	 */
	public boolean admin_pannel() {
		myLogger.info("************************ADMIN Opreation PANEL************************\n");
		adminpanel: while (true) {
			System.out.println("1. CRUD Patient " + "\n2. CRUD Doctor" + "\n3. CRUD Drugs " + "\n4. CRUD Lab Test"
					+ "\n5. Book appointment for a patient by selecting Patient, " + // "\n6. Doctor and Data/time"+
					"\n6. Exits\n\n");
			System.out.print("\nEnter Choice The Option----");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1: {
				myLogger.info("Patient Crud Operation");
				patientCrud.patientPanel();
				break;
			}
			case 2:
				myLogger.info("Doctor Crud Operation");
				crudDoctor.DoctorPanel();
				break;
			case 3:
				myLogger.info("Drugs Crud Operation");
				drugsCrud.DrugsPanel();
				break;
			case 4:
				myLogger.info("Labtest Crud Operation");
				labTestCrud.labTestPanel();
				break;
			case 5:
				myLogger.info("Appointment Operation");

				appoinment.appoinmentPanel();
				break;
			case 6:
				break adminpanel;
			default:
				myLogger.warn("----Worng Choioce---- \n");
				continue;
			}
		}
		return true;
	}

	@PostConstruct
	public void start() {
		myLogger.debug("Admin Panel :");
		myLogger.info("Admin Panel :");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Admin Panel :");
		myLogger.info(" Admin Panel :");
	}
}
