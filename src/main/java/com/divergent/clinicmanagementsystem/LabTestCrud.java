package com.divergent.clinicmanagementsystem;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.divegent.doa.LabTestCrudDOA;
import com.divergent.clinicmanagementsystem.dto.LabTestDto;
import com.divergent.databaseconnection.JDBCConnection;

public class LabTestCrud {
	private static final Logger myLogger = Logger
			.getLogger("com.divergent.clinicmanagementsystem.ClinicManagementSystem");
	public Scanner scobj = new Scanner(System.in);

	public void labTestPanel() {

		p_panel: while (true) {
			myLogger.info("\n************************LabTest CRUD************************\n");

			// System.out.println("\n************************LabTest
			// CRUD************************\n");
			System.out.println("1:LabTest Create ");
			System.out.println("2:LabTest Read ");
			System.out.println("3:LabTest Update  ");
			System.out.println("4:LabTest Delete ");
			System.out.println("5:Exit \n");
			System.out.print("\nEnter Choice The Option----  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Lab Test Create");
				labTestCreate();
				break;
			case 2:
				myLogger.info(" Lab Test Read" + "");
				labTestRead();
				break;
			case 3:
				myLogger.info("Lab Test Update");
				labTestUpdate();
				break;
			case 4:
				myLogger.info("Lab Test Delete");
				labTestDelete();
				break;
			case 5:
				break p_panel;
			default:
				// throw new IllegalArgumentException("Unexpected value: " + choice);
				myLogger.warning("--- -Worng Choioce---- \n");
				// System.out.println("--- -Worng Choioce---- \n");
				continue;
			}
		}

	}

	private void labTestUpdate() {
		LabTestCrudDOA doa = new LabTestCrudDOA(new JDBCConnection());
		labTestRead();
		System.out.println("Enter  Drugs Id Which You Want to Update");
		int rowid = scobj.nextInt();
		String labtest_name;
		int labtest_price;
		System.out.print("\nLabTest Id----" + rowid);
		scobj.nextLine();
		System.out.print("\nEnter LabTest Name  --");
		labtest_name = scobj.nextLine().trim();

		System.out.print("\nEnter LabTest Price  --");
		labtest_price = scobj.nextInt();
		try {
			doa.update(rowid, labtest_name, labtest_price);

			myLogger.info("\n-------Value Has Updated-------");
			// System.out.println("\n-------Value Has Updated-------");
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			// TODO: handle exception
		}

	}

	private void labTestRead() {
		LabTestCrudDOA doa = new LabTestCrudDOA(new JDBCConnection());
		try {
			List<LabTestDto> list = doa.read();
			System.out.printf(" LabTest Id\t  LabTest Name \t LabTest price \n");
			for (LabTestDto labTestDto : list) {
				System.out.printf("%5s %20s %15s", labTestDto.getLabtestid(), labTestDto.getLabtest_name(),
						labTestDto.getLabtest_price());
				System.out.println("\n");
			}
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			// System.err.println(e);
		}

	}

	private void labTestCreate() {
		LabTestCrudDOA doa = new LabTestCrudDOA(new JDBCConnection());
		int labtest_id;
		String labtest_name;
		int labtest_price;
		System.out.print("\nEnter LabTest Id  --");
		labtest_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter LabTest Name  --");
		labtest_name = scobj.nextLine().trim();

		System.out.print("\nEnter LabTest Price  --");
		labtest_price = scobj.nextInt();
		try {
			doa.create(labtest_id, labtest_name, labtest_price);
			myLogger.info("\n-------Value Has Inserted-------");
			// System.out.println("\n-------Value Has Inserted-------");
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.warning("\n-------Value Has Not Inserted-------");
			/// System.out.println("\n-------Value Has Not Inserted-------");
		}

	}

	private void labTestDelete() {
		LabTestCrudDOA doa = new LabTestCrudDOA(new JDBCConnection());
		System.out.print("\n----Enter LABTEST ID  To Delete Drug --");
		int a = scobj.nextInt();

		try {
			doa.delete(a);
			myLogger.info("\n----Lab Test Delete----\n");
			// System.out.println("\n----Lab Test Delete----\n");
		} catch (Exception e) {
			myLogger.warning(e.getMessage());
			myLogger.warning("\n---- LabTest Not Delete----\n");
			// System.out.println("\n---- LabTest Not Delete----\n");
		}

	}

}
