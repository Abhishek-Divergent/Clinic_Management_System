package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * {@link JDBCConnection} implement to {@link JDBCConnectionInterface}
 * JDBCConnection Class Establish the connection to Date Base
 * 
 * @author JAI MAHAKAL
 *
 */
public class JDBCConnection implements JDBCConnectionInterface {
	private String name = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/clinicmanagementsystem";

	/**
	 * connection method Establish the connection with Date Base
	 * 
	 * @return connection
	 */
	public Connection connection() {
		try {
			return DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
		System.out.print("Connetion Faild Run Again");
		}
		return null;
	}

}
