package com.divegent.doa;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.databaseconnection.H2DatabaseManager;
import com.divergent.doa.AppoinmentDOA;
import com.divergent.dto.AppoinmentDto;

class AppoinmentDOATest {
	Connection connection = null;
	H2DatabaseManager h2DatabaseManager = null;
	Statement statement;

	@SuppressWarnings("resource")
	@Before
	private void TestSetup() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		h2DatabaseManager = (H2DatabaseManager) context.getBean("h2DatabaseManager");
		connection = h2DatabaseManager.connection();
		statement = connection.createStatement();
		statement.execute("drop table if exists appoinment");
		System.out.println("Table Deleted");
		statement.executeUpdate(
				"create table if not exists  appoinment(appoinment_id int auto_increment, patient_id int, doc_id int ,doc_name varchar(20), patient_name varchar(20),problem varchar(20) ,date varchar(20) ,time varchar(20))");
		System.out.println("Table created successfully...");
		statement.executeUpdate(
				" insert into appoinment values(2, 1002, 104, 'jayant', 'sadjfaf', 'cold', '2021-03-20', '00:00:00')");
		System.out.println("inserted successfully...");

	}

	@SuppressWarnings("resource")
	@Test
	void testCreate() throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");

		AppoinmentDOA appoinmentDOA = (AppoinmentDOA) context.getBean("appoinmentDOA");
		assertEquals(1, appoinmentDOA.create(1, 1003, 105, "pappu", "ramu", "prem", "2021-03-25", "10:20:20"));
	}

	@SuppressWarnings("resource")
	@Test
	void testDelete() throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");

		AppoinmentDOA appoinmentDOA = (AppoinmentDOA) context.getBean("appoinmentDOA");
		assertEquals(1, appoinmentDOA.delete(2));

	}

	@SuppressWarnings("resource")
	@Test
	void testRead() throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");

		AppoinmentDOA appoinmentDOA = (AppoinmentDOA) context.getBean("appoinmentDOA");
		List<AppoinmentDto> list = appoinmentDOA.read();
		assertFalse(list.isEmpty());
	}

}
