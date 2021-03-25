package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DatabaseManager implements JDBCConnectionInterface {

	public static String DB_URL = "jdbc:h2:mem:";

	@Override
	public Connection connection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL	);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
