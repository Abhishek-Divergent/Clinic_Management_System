package com.divegent.doa;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.divergent.databaseconnection.H2DatabaseManager;

class DoctorCrudDOATest {

	Connection connection = null;

	@BeforeEach
	void setUp() throws Exception {
		H2DatabaseManager driverManager = new H2DatabaseManager();
		connection = driverManager.connection();
	}

	@Test
	void testCreate() throws SQLException {
		var stm = connection.createStatement();
		var rs = stm.executeQuery("SELECT 1+1");

		if (rs.next()) {

			assertEquals(2, rs.getInt(1));
		}

	}

	void tearDown() throws Exception {

	}


}
