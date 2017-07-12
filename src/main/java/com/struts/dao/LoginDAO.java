package com.struts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

	/*
	 *  This is a method to get connection to database
	 */
	
	public Connection getConnection() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");
		
		return connection;
	}
	
	/**
	 * This method checks the details entered by user with the database details
	 * and returns if the user matches or not.
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	
	public boolean checkUserDetailsWithDB(String username, String password) throws Exception {
		boolean isUserValid = false;
		
		Connection connection = getConnection();
		String sql = "select name, password from pioneer_students where name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet resultSet = statement.executeQuery();
		String usernameFromDB = null;
		String passwordFromDB = null;
		
		while(resultSet.next()) {
			usernameFromDB = resultSet.getString(1);
			passwordFromDB = resultSet.getString(2);
		}
		
		if(username.equals(usernameFromDB) && password.equals(passwordFromDB)){
			isUserValid = true;
		}
		return isUserValid;
	}
	
}
