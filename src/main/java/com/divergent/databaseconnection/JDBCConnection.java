package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * {@link JDBCConnection} implement to {@link JDBCConnectionInterface}
 * JDBCConnection Class Establish the connection to Date Base
 * 
 * @author JAI MAHAKAL
 *
 */
public class JDBCConnection implements JDBCConnectionInterface {

	private static final Logger myLogger = Logger.getLogger("com.divergent.clinicmanagementsystem");
	
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
			myLogger.warning(e.getMessage());
			myLogger.info("Connetion Faild Run Again");
		}
		return null;
	}

}
