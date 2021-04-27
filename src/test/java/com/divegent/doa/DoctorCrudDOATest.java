package com.divegent.doa;



import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.clinicmanagementsystem.doa.DoctorCrudDOA;
import com.divergent.databaseconnection.H2DatabaseManager;

class DoctorCrudDOATest {

	Connection connection = null;
	H2DatabaseManager databaseManager = null;
	Statement statement = null;

	@Before
	void setup() throws Exception {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		databaseManager = (H2DatabaseManager) context.getBean("h2databaseid");
		connection = databaseManager.connection();
		statement = connection.createStatement();
		statement.execute("drop table if exists doctor");
		System.out.println("Table Deleted");
		statement.executeUpdate(
				"create table if not exists doctor (doc_id int auto_increment, doc_username varchar(30), doc_password varchar(15) , doc_name varchar(20), doc_contact varchar(20),doc_speciality varchar(20),doc_fees int)");
		System.out.println("Table created successfully...");

		statement.execute("insert into doctor values(101, 'jayant', '123456789', 'jayant','4561230', 'dentist', 1000)");
		System.out.println("Data inserted successfully...");

	}

	@Test
	public void read() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		DoctorCrudDOA crudDOA =(DoctorCrudDOA) context.getBean("doctordaoid");
			List<Map<Integer, String>> list=crudDOA.read();
        assertEquals(false,list.isEmpty());			
	}
	@Test
	public void create() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		DoctorCrudDOA crudDOA =(DoctorCrudDOA) context.getBean("doctordaoid");
		assertEquals(1,crudDOA.create(102, "kallu", "123456789", "jayant patel","4561230", "dentist", 1000));			

	}
	
	@Test
	public void update() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		DoctorCrudDOA crudDOA =(DoctorCrudDOA) context.getBean("doctordaoid");
		assertEquals(1,crudDOA.update(101, "kallu", "123456789", "jayant  patel","4561230", "dentist", 2000));			

	}
	
	@Test
	public  void delete() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("testconfig.xml");
		DoctorCrudDOA crudDOA =(DoctorCrudDOA) context.getBean("doctordaoid");
		assertEquals(1,crudDOA.delete(101));			
	}
	
	

}
