package com.divergent.clinicmanagementsystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.divegent.doa.DrugCrudDOA;
import com.divergent.clinicmanagementsystem.dto.DrugsDto;
import com.divergent.databaseconnection.JDBCConnection;

public class DrugsCrud {
	private static final Logger myLogger = Logger
			.getLogger("com.divergent.clinicmanagementsystem.ClinicManagementSystem");
	public Scanner scobj = new Scanner(System.in);

	public void DrugsPanel() {

		p_panel: while (true) {
			myLogger.info("\n************************Drugs CRUD************************\n");

			// System.out.println("\n************************Drugs
			// CRUD************************\n");
			System.out.println("1: Drugs Create");
			System.out.println("2: Drugs Read");
			System.out.println("3: Drugs Update");
			System.out.println("4: Drugs Delete");
			System.out.println("5: Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info(" Drugd Create:");
				DrugsCreate();
				break;
			case 2:
				myLogger.info(" Drugd Read:");
				DrugsRead();
				break;
			case 3:
				myLogger.info(" Drugd Update:");
				DrugsUpdate();
				break;
			case 4:
				myLogger.info(" Drugd Delete:");
				DrugsDelete();
				break;
			case 5:
				break p_panel;
			default:
				// throw new IllegalArgumentException("Unexpected value: " + choice);
				myLogger.warning("--- -Worng Choioce---- \n");
				//System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}
	}

	private void DrugsUpdate() {
		DrugCrudDOA doa = new DrugCrudDOA(new JDBCConnection());
		String Drugs_name;
		String Drugs_description;
		DrugsRead();
		System.out.print("\n----Enter DrugsID Which You Want to UPDATE --");
		int rowid = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nDrug Id Is----" + rowid + "----");
		System.out.print("\nEnter Drug Name --");
		Drugs_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Drugs Description  --");
		Drugs_description = scobj.nextLine().trim();
		try {
			doa.update(rowid, Drugs_name, Drugs_description);
			
			myLogger.info("\n-------Value Has Updated-------");
			//System.out.println("\n-------Value Has Updated-------");
		} catch (Exception e) {
			   myLogger.warning(e.getMessage());
			myLogger.info("\n-------Value Has Updated-------");
			//System.out.println("\n-------Value Has Not Updated-------");
		}
	}

	private void DrugsRead() {
		DrugCrudDOA doa = new DrugCrudDOA(new JDBCConnection());
		try {
			List<DrugsDto> drugsDtos = doa.read();
			System.out.printf("DrugID \t DrugName \t DrugDiscription\n");
			for (DrugsDto drugsDto : drugsDtos) {
				System.out.printf("%10s\t|| %10s\t|| %10s ", drugsDto.getDrugsid(), drugsDto.getDrugsname(),
						drugsDto.getDrugsdescription());
				System.out.println("");
			}
		} catch (SQLException e) {
	        myLogger.warning(e.getMessage());
			//e.printStackTrace();
		}

	}

	private void DrugsCreate() {

		int Drugs_id;
		String Drugs_name;
		String Drugs_description;

		System.out.print("\nEnter Drug Id  --");
		Drugs_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Drug Name  --");
		Drugs_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Drugs_description  --");
		Drugs_description = scobj.nextLine().trim();
		DrugCrudDOA doa = new DrugCrudDOA(new JDBCConnection());
		try {
			doa.create(Drugs_id, Drugs_name, Drugs_description);
			myLogger.info("\n-------Value Has Updated-------");
			//System.out.println("\n-------Value Has Inserted-------");
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.info("\n-------Value Has Not  Updated-------");
		}
	}

	private void DrugsDelete() {
		DrugCrudDOA doa = new DrugCrudDOA(new JDBCConnection());
		try {
			System.out.print("\n----Enter Drug ID  To Delete Drug --");
			int a = scobj.nextInt();
			doa.delete(a);
			myLogger.info("\n---- Drug Delete----\n");
			//System.out.println("\n---- Drug Delete----\n");
		} catch (Exception e) {
			// System.err.println(e);
			myLogger.info("\n---- Drug Not Delete----\n");
			//System.out.println("\n---- Drug Not Delete----\n");
		}

	}

}
