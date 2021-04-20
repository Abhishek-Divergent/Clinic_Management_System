package com.divergent.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.divergent.doa.LabTestCrudDOA;
import com.divergent.dto.LabTestDto;

public class LabTestCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(LabTestCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	private LabTestCrudDOA doa;

	public void setScobj(Scanner scobj) {
		this.scobj = scobj;
	}

	public void setDoa(LabTestCrudDOA doa) {
		this.doa = doa;
	}

	public void labTestPanel() {

		p_panel: while (true) {
			myLogger.info("\n************************LabTest CRUD************************\n");
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
				myLogger.warn("--- -Worng Choioce---- \n");

				continue;
			}
		}

	}

	private void labTestUpdate() {
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
			int i = doa.update(rowid, labtest_name, labtest_price);
			if (i == 1) {
				myLogger.info("\n-------Value Has Updated-------");
			} else {
				myLogger.info("\n-------Value Has Not Updated-------");
			}
		} catch (Exception e) {
			myLogger.warn(e.getMessage());
			myLogger.error(e.getMessage());

		}

	}

	private void labTestRead() {
		try {
			List<LabTestDto> list = doa.read();
			System.out.printf(" LabTest Id\t  LabTest Name \t LabTest price \n");
			for (LabTestDto labTestDto : list) {
				System.out.printf("%5s %20s %15s", labTestDto.getLabtestid(), labTestDto.getLabtest_name(),
						labTestDto.getLabtest_price());
				System.out.println("\n");
			}
		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

	private void labTestCreate() {
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
			int i = doa.create(labtest_id, labtest_name, labtest_price);
			if (i == 1) {
				myLogger.info("\n-------Value Has Inserted-------");
			} else {
				myLogger.info("\n-------Value Has Not Inserted-------");
			}
		} catch (Exception e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());
		}

	}

	private void labTestDelete() {
		System.out.print("\n----Enter LABTEST ID  To Delete Drug --");
		int a = scobj.nextInt();

		try {
			int i = doa.delete(a);
			if (i == 1) {
				myLogger.info("\n----Lab Test Delete----\n");

			} else {
				myLogger.info("\n----Lab Test  Not Delete----\n");

			}

		} catch (Exception e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

}
