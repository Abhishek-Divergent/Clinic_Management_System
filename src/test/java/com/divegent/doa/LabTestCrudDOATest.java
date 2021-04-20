package com.divegent.doa;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.divergent.databaseconnection.H2DatabaseManager;
import com.divergent.doa.LabTestCrudDOA;
import com.divergent.dto.LabTestDto;

class LabTestCrudDOATest {
	
	
	Connection connection=null;
    H2DatabaseManager databaseManager=null;
	Statement statement;
	
	@BeforeEach
	private void TestSetup() throws SQLException {
		databaseManager = new H2DatabaseManager();
		connection = databaseManager.connection();
		statement = connection.createStatement();
		statement.execute("drop table if exists labtest");
		System.out.println("Table Deleted");
		statement.executeUpdate("create table if not exists  labtest(labtest_id int auto_increment, labtest_name varchar(30), labtest_price int )");
		System.out.println("Table created successfully...");
		statement.executeUpdate(" insert into labtest values(1001,'Blood Test',45215)");
		System.out.println("Data inserted successfully...");

	}
	
	@Test
	void testDelete() throws SQLException {
		 LabTestCrudDOA crudDOA= new LabTestCrudDOA(databaseManager);
		  assertEquals(1,crudDOA.delete(1001));
	}

	@Test
	void testCreate() throws SQLException {
		 LabTestCrudDOA crudDOA= new LabTestCrudDOA(databaseManager);
		  assertEquals(1,crudDOA.create(1002,"sugar test" , 20015));
	}

	@Test
	void testRead() throws SQLException {
		 LabTestCrudDOA crudDOA= new LabTestCrudDOA(databaseManager);
		 List<LabTestDto> list =crudDOA.read();
		 assertFalse(list.isEmpty());
 	}

	@Test
	void testUpdate() throws SQLException {
		 LabTestCrudDOA crudDOA= new LabTestCrudDOA(databaseManager);
		  assertEquals(1,crudDOA.update(1001,"test karo", 1000));
	}

}
