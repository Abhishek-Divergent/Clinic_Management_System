package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

	private static final Logger myLogger = LoggerFactory.getLogger(JDBCConnection.class.getName());
	@Value("${spring.datasource.username}")
	private String name;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;

	/**
	 * connection method Establish the connection with Date Base
	 * 
	 * @return connection
	 */
	public Connection connection() {
		try {
			return DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			myLogger.error(e.getMessage());
			myLogger.warn(e.getMessage());
			myLogger.warn("!Connetion Faild Run Again ");
			System.exit(100);
		}
		return null;
	}

}
