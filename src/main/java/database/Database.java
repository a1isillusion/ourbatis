package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
public static String jdbc="com.mysql.jdbc.Driver"; 
public static String url="jdbc:mysql://localhost:3306/hotel";
public static String user="root";
public static String password="1234";
static {
	try {
		Class.forName(jdbc);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
public static Connection getConnection() {
	Connection connection=null;
	try {
		connection=DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return connection;
}
}
