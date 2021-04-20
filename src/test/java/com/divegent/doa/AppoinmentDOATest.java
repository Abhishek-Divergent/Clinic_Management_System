package com.divegent.doa;

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
import com.divergent.doa.AppoinmentDOA;
import com.divergent.dto.AppoinmentDto;

class AppoinmentDOATest {
	private Connection connection=null;
    private H2DatabaseManager databaseManager=null;
    private AppoinmentDOA appoinmentDOA;
   

	Statement statement;
	
	@BeforeEach 
	private void TestSetup() throws SQLException {
		 ApplicationContext applicationContext= new ClassPathXmlApplicationContext("testconfig.xml");
		 appoinmentDOA=(AppoinmentDOA) applicationContext.getBean("appoinmentdoaid");

		
		statement = connection.createStatement();
		statement.execute("drop table if exists appoinment");
		System.out.println("Table Deleted");
		statement.executeUpdate("create table if not exists  appoinment(appoinment_id int auto_increment, patient_id int, doc_id int ,doc_name varchar(20), patient_name varchar(20),problem varchar(20) ,date varchar(20) ,time varchar(20))");
		System.out.println("Table created successfully...");
		statement.executeUpdate(" insert into appoinment values(2, 1002, 104, 'jayant', 'sadjfaf', 'cold', '2021-03-20', '00:00:00')");
		System.out.println("inserted successfully...");
		
		
		
	}

	
	
	
	
	

	@Test
	void testCreate() throws SQLException {
		 ApplicationContext applicationContext= new ClassPathXmlApplicationContext("testconfig.xml");
		 appoinmentDOA=(AppoinmentDOA) applicationContext.getBean("appoinmentdoaid");
		assertEquals(1, appoinmentDOA.create(1, 1003, 105,"pappu", "ramu", "prem", "2021-03-25", "10:20:20"));
	}

	@Test
	void testDelete() throws SQLException {
		 ApplicationContext applicationContext= new ClassPathXmlApplicationContext("testconfig.xml");
		 appoinmentDOA=(AppoinmentDOA) applicationContext.getBean("appoinmentdoaid");
	assertEquals(1, appoinmentDOA.delete(2));
	
	}

	@Test
	void testRead() throws SQLException {
		 ApplicationContext applicationContext= new ClassPathXmlApplicationContext("testconfig.xml");
		 appoinmentDOA=(AppoinmentDOA) applicationContext.getBean("appoinmentdoaid");
		List<AppoinmentDto> list=appoinmentDOA.read();
		assertFalse(list.isEmpty());
	}

}
