package com.divergent.clinicmanagementsystem.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

/**
 * {@link JDBCConnection} implement to {@link JDBCConnectionInterface}
 * JDBCConnection Class Establish the connection to Date Base
 * 
 * @author JAI MAHAKAL
 *
 */
@Repository
public class JDBCConnection implements JDBCConnectionInterface {
	@Autowired
	private Environment environment;
	private static final Logger myLogger = LoggerFactory.getLogger(JDBCConnection.class.getName());

	// @Value("${spring.datasource.username}")
	private String NAME = "spring.datasource.username";

	// @Value("${spring.datasource.password}")
	private String PASSWORD = "spring.datasource.password";

	// @Value("${spring.datasource.url}")
	private String URL = "spring.datasource.url";

	/**
	 * connection method Establish the connection with Date Base
	 * 
	 * @return connection
	 */
	public Connection connection() {
		try {
			return DriverManager.getConnection(environment.getProperty(URL), environment.getProperty(NAME),
					environment.getProperty(PASSWORD));
		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());
			myLogger.warn("!Connetion Faild Run Again ");
			System.exit(0);
		}
		return null;
	}

}
