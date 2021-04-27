package com.divergent.clinicmanagementsystem.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.doa.LabTestCrudDOA;
import com.divergent.clinicmanagementsystem.dto.LabTestDto;

@Component
public class LabTestCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(LabTestCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private LabTestCrudDOA labTestCrudDOA;
	@Autowired
	private ApplicationContext applicationContext;

	public void labTestPanel() {

		p_panel: while (true) {
			myLogger.info("\n************************LabTest CRUD************************\n");
			System.out.println("1:LabTest Create : ");
			System.out.println("2:LabTest Read : ");
			System.out.println("3:LabTest Update : ");
			System.out.println("4:LabTest Delete : ");
			System.out.println("5:Exit :  \n");
			System.out.print("\nEnter Choice The Option----:  ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Lab Test Create : ");
				labTestCreate();
				break;
			case 2:
				myLogger.info(" Lab Test Read : ");
				labTestRead();
				break;
			case 3:
				myLogger.info("Lab Test Update : ");
				labTestUpdate();
				break;
			case 4:
				myLogger.info("Lab Test Delete : ");
				labTestDelete();
				break;
			case 5:
				break p_panel;
			default:
				myLogger.warn("----Worng Choioce----\n");

				continue;
			}
		}

	}

	private void labTestUpdate() {
		labTestRead();
		System.out.println("Enter  Drugs Id Which You Want to Update : ");
		int rowid = scobj.nextInt();
		String labtest_name;
		int labtest_price;
		System.out.print("\nLabTest Id----: " + rowid);
		scobj.nextLine();
		System.out.print("\nEnter LabTest Name  --: ");
		labtest_name = scobj.nextLine().trim();

		System.out.print("\nEnter LabTest Price  --: ");
		labtest_price = scobj.nextInt();
		LabTestDto labTestDto = applicationContext.getBean(LabTestDto.class);
		labTestDto.setLabtestid(rowid);
		labTestDto.setLabtest_name(labtest_name);
		labTestDto.setLabtest_price(labtest_price);

		Boolean result = LabTestDto.validator(labTestDto);
		if (result) {
			try {
				int i = labTestCrudDOA.update(rowid, labtest_name, labtest_price);
				if (i > 0) {
					myLogger.info("\n-------Value Has Updated-------");
				} else {
					myLogger.info("\n-------Value Has Not Updated-------");
				}
			} catch (Exception e) {
				myLogger.warn(e.getMessage());
				myLogger.error(e.getMessage());

			}
		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			labTestCreate();
		}
	}

	private void labTestRead() {
		try {
			List<Map<String, Object>> list = labTestCrudDOA.read();
			if (list != null) {
				System.out.printf("\tLabTest Id\t  LabTest Name \t LabTest price \n");
				for (Map<String, Object> map : list) {
					for (Entry<String, Object> pair : map.entrySet()) {
						System.out.printf("%15s", pair.getValue());
					}
					System.out.println();
				}
			} else {
				myLogger.info("List Is Null ");
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
		System.out.print("\nEnter LabTest Id  --: ");
		labtest_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter LabTest Name  --: ");
		labtest_name = scobj.nextLine().trim();

		System.out.print("\nEnter LabTest Price  --: ");
		labtest_price = scobj.nextInt();
		LabTestDto labTestDto = applicationContext.getBean(LabTestDto.class);
		labTestDto.setLabtest_name(labtest_name);
		labTestDto.setLabtestid(labtest_id);
		labTestDto.setLabtest_price(labtest_price);
		
		Boolean result = LabTestDto.validator(labTestDto);
		if (result) {
			try {
				int i = labTestCrudDOA.create(labtest_id, labtest_name, labtest_price);
				if (i > 0) {
					myLogger.info("\n-------Value Has Inserted-------");
				} else {
					myLogger.info("\n-------Value Has Not Inserted-------");
				}
			} catch (Exception e) {
				myLogger.error(e.getMessage());
				myLogger.warn(e.getMessage());
			}

		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			labTestCreate();
		}
	}

	private void labTestDelete() {
		System.out.print("\n----Enter LABTEST ID  To Delete Drug --");
		int a = scobj.nextInt();

		try {
			int i = labTestCrudDOA.delete(a);
			if (i > 0) {
				myLogger.info("\n----Lab Test Delete----\n");

			} else {
				myLogger.info("\n----Lab Test  Not Delete----\n");

			}

		} catch (Exception e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Lab Test Crud  Opeation Panel Start : ");
		myLogger.info("  Lab Test  Crud  Opeation Panel Start : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Lab Test  Crud Opeation Panel End : ");
		myLogger.info(" Lab Test  Crud  Opeation Panel End : ");

	}
}
