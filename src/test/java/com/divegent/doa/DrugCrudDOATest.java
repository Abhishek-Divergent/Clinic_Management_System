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
import com.divergent.doa.DrugCrudDOA;
import com.divergent.dto.DrugsDto;

class DrugCrudDOATest {
	Connection connection=null;
    H2DatabaseManager databaseManager=null;
	Statement statement;
	
	@BeforeEach
	private void TestSetup() throws SQLException {
		databaseManager = new H2DatabaseManager();
		connection = databaseManager.connection();
		statement = connection.createStatement();
		statement.execute("drop table if exists drugs");
		System.out.println("Table Deleted");
		statement.executeUpdate("create table if not exists  drugs(drugs_id int auto_increment, drugs_name varchar(30), drugs_description varchar(30) )");
		System.out.println("Table created successfully...");
		statement.executeUpdate(" insert into drugs values(2001,'peracetamol','fever')");

	}


	@Test
	void testDelete() throws SQLException {
		 DrugCrudDOA crudDOA= new DrugCrudDOA(databaseManager);
		  assertEquals(1,crudDOA.delete(2001));
	}

	@Test
	void testCreate() throws SQLException {
		 DrugCrudDOA crudDOA= new DrugCrudDOA(databaseManager);
		assertEquals(1,crudDOA.create(2002,"allegra","alergy"));
	}

	@Test
	void testRead() throws SQLException {
		 DrugCrudDOA crudDOA= new DrugCrudDOA(databaseManager);
		 List<DrugsDto> list =crudDOA.read();
		 assertFalse(list.isEmpty());
	}

	@Test   
	void testUpdate() throws SQLException {
		 DrugCrudDOA crudDOA= new DrugCrudDOA(databaseManager);
		 assertEquals(1,crudDOA.update(2001,"pata nhi","fever"));
	}

}
