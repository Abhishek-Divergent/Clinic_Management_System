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
import org.springframework.stereotype.Component;

import com.divergent.doa.DrugCrudDOA;

@Component
public class DrugsCrud {
	private static final Logger myLogger = LoggerFactory.getLogger(DrugsCrud.class.getName());
	private Scanner scobj = new Scanner(System.in);
	@Autowired
	private DrugCrudDOA drugCrudDOA;

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
	}

	private void DrugsRead() {
		try {
			List<Map<Integer, String>> list = drugCrudDOA.read();
			System.out.printf("Drug ID \t Drug Name \t Drug Discription\n");
			if (list != null) {
				for (Map<Integer, String> map : list) {
					System.out.printf("%10s\t|| %10s\t|| %10s ", map.get(1), map.get(2), map.get(3));
					System.out.println("");
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
		try {
			int i = drugCrudDOA.create(Drugs_id, Drugs_name, Drugs_description);
			if (i > 0) {
				myLogger.info("\n-------Value Has Updated-------");
			} else {
				myLogger.info("\n-------Value Has Not  Updated-------");
			}
		} catch (Exception e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());
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
