package javaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mariadb://localhost/segurancaExame";
		String user = "root";
		String password = "";
		
		return DriverManager.getConnection(url, user, password);
	}

}