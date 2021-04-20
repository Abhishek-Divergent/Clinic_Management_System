package com.divegent.doa;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.databaseconnection.H2DatabaseManager;
import com.divergent.doa.PatientCrudDOA;
import com.divergent.dto.PatientDto;

class PatientCrudDOATest {
	Connection connection = null;
	H2DatabaseManager databaseManager = null;
	Statement statement;

	@BeforeEach
	private void TestSetup() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		databaseManager = (H2DatabaseManager) context.getBean("h2databaseid");
		connection = databaseManager.connection();
		statement = connection.createStatement();
		statement.execute("drop table if exists patient");
		System.out.println("Table Deleted");
		statement.executeUpdate(
				"create table if not exists  patient(p_id int auto_increment, p_name varchar(30), p_age int , p_gender varchar(30), p_contact varchar(30), p_weight int , p_address varchar(30))");
		System.out.println("Table created successfully...");
		statement.executeUpdate(
				" insert into patient values(1001, 'Kallu Gupta', '20', 'M', '8451263252', 45, 'near sbi bhopal')");
		System.out.println("Data inserted successfully...");

	}

	@Test
	void testCreate() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		PatientCrudDOA crudDOA = (PatientCrudDOA) context.getBean("patientdoaid");
		assertEquals(1, crudDOA.create(1002, "rammu gupta", 30, "male", "812009076", 25, "near harpalpur"));
	}

	@Test
	void testDelete() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		PatientCrudDOA crudDOA = (PatientCrudDOA) context.getBean("patientdoaid");
		assertEquals(1, crudDOA.delete(1001));
	}

	@Test
	void testRead() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		PatientCrudDOA crudDOA = (PatientCrudDOA) context.getBean("patientdoaid");
		List<PatientDto> list = crudDOA.read();
		assertFalse(list.isEmpty());
	}

	@Test
	void testUpdate() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		PatientCrudDOA crudDOA = (PatientCrudDOA) context.getBean("patientdoaid");
		assertEquals(1, crudDOA.update(1001, "ram gupta", 25, "male", "12345561", 50, "indore"));
	}

}