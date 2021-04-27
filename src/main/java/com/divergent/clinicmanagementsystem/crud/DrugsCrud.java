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

import com.divergent.clinicmanagementsystem.doa.DrugCrudDOA;
import com.divergent.clinicmanagementsystem.dto.DrugsDto;

@Component
public class DrugsCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(DrugsCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private DrugCrudDOA drugCrudDOA;
	@Autowired
	private ApplicationContext applicationContext;

	public void DrugsPanel() {

		p_panel: while (true) {
			myLogger.info("\n************************Drugs CRUD************************\n");
			System.out.println("1: Drugs Create : ");
			System.out.println("2: Drugs Read : ");
			System.out.println("3: Drugs Update : ");
			System.out.println("4: Drugs Delete : ");
			System.out.println("5: Exit \n");
			System.out.print("\nEnter Choice The Option----: ");
			int choice = scobj.nextInt();
			switch (choice) {
			case 1:
				myLogger.info("Drugd Create : ");
				DrugsCreate();
				break;
			case 2:
				myLogger.info("Drugd Read : ");
				DrugsRead();
				break;
			case 3:
				myLogger.info("Drugd Update : ");
				DrugsUpdate();
				break;
			case 4:
				myLogger.info("Drugd Delete : ");
				DrugsDelete();
				break;
			case 5:
				break p_panel;
			default:
				myLogger.warn("----Worng Choioce----\n");
				continue;
			}
		}
	}

	private void DrugsUpdate() {
		String Drugs_name;
		String Drugs_description;
		DrugsRead();
		System.out.print("\n----Enter DrugsID Which You Want to UPDATE ----:");
		int rowid = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nDrug Id Is ---- :" + rowid + " :---- ");
		System.out.print("\nEnter Drug Name -- : ");
		Drugs_name = scobj.nextLine().trim();

		System.out.print("\nEnter  Drugs Description  -- : ");
		Drugs_description = scobj.nextLine().trim();

		DrugsDto drugsDto = applicationContext.getBean(DrugsDto.class);
		drugsDto.setDrugsdescription(Drugs_description);
		drugsDto.setDrugsname(Drugs_description);
		Boolean result = DrugsDto.validator(drugsDto);
		if (result) {
			try {

				int i = drugCrudDOA.update(rowid, Drugs_name, Drugs_description);
				if (i > 0) {
					myLogger.info("\n-------Value Has Updated------- : ");
				} else {
					myLogger.info("\n-------Value Has Updated------- : ");

				}
			} catch (Exception e) {
				myLogger.warn(e.getMessage());
				myLogger.error(e.getMessage());
			}
		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			DrugsCreate();
		}

	}

	private void DrugsRead() {
		try {
			List<Map<String, Object>> list = drugCrudDOA.read();
			System.out.printf("  \t \t \tDrug ID   \t \t Drug Name   \t   Drug Discription\n");
			if (list != null) {
				for (Map<String, Object> map : list) {
					for (Entry<String, Object> pair : map.entrySet()) {
						System.out.printf("%30s \t", pair.getValue());
					}
					System.out.println();
				}
			} else {
				myLogger.info("List is Null");
			}

		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

	private void DrugsCreate() {
		int Drugs_id;
		String Drugs_name;
		String Drugs_description;

		System.out.print("\nEnter Drug Id  -- : ");
		Drugs_id = scobj.nextInt();
		scobj.nextLine();
		System.out.print("\nEnter Drug Name  -- : ");
		Drugs_name = scobj.nextLine().trim();
		System.out.print("\nEnter  Drugs_description  -- : ");
		Drugs_description = scobj.nextLine().trim();

		DrugsDto drugsDto = applicationContext.getBean(DrugsDto.class);
		drugsDto.setDrugsdescription(Drugs_description);
		drugsDto.setDrugsid(Drugs_id);
		drugsDto.setDrugsname(Drugs_name);
		Boolean result = DrugsDto.validator(drugsDto);
		if (result) {
			try {
				int i = drugCrudDOA.create(Drugs_id, Drugs_name, Drugs_description);
				if (i > 0) {
					myLogger.info("\n-------Data Has  Inserted-------");
				} else {
					myLogger.info("\n-------Data Has Not Inserted-------");
				}
			} catch (Exception e) {
				myLogger.error(e.getMessage());
				myLogger.warn(e.getMessage());
			}
		} else {
			myLogger.info("\n-------Data Has Not Inserted-------");
			myLogger.info("\n-------Enter Again Data -------");
			DrugsCreate();
		}

	}

	private void DrugsDelete() {
		try {
			System.out.print("\n----Enter Drug ID  To Delete Drug -- : ");
			int a = scobj.nextInt();
			int i = drugCrudDOA.delete(a);
			if (i > 0) {
				myLogger.info("\n---- Drug Delete----\n");
			} else {
				myLogger.info("\n---- Drug Not Delete----\n");
			}

		} catch (Exception e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());

		}

	}

	@PostConstruct
	public void start() {
		myLogger.debug(" Drugs Crud Opeation Panel Start : ");
		myLogger.info("  Drugs Crud Opeation Panel Start : ");

	}

	@PreDestroy
	public void end() {
		myLogger.debug(" Drugs Crud Opeation Panel End : ");
		myLogger.info(" Drugs Crud Opeation Panel End : ");

	}

}
