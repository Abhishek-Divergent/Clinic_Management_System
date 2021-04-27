package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.divergent.clinicmanagementsystem.databaseconnection.JDBCConnectionInterface;

public class H2DatabaseManager implements JDBCConnectionInterface {

	public static String DB_URL = "jdbc:h2:~/test";

	@Override
	public Connection connection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL,"sa",""	);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return connection;
	}

}
