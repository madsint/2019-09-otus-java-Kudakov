package ru.otus.l111.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {

	private static Connection connection;

	private ConnectionHelper() {

	}

	private static Connection createConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/orm";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "admin");
		return DriverManager.getConnection(url, props);
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = createConnection();
		}
		return connection;
	}

}
