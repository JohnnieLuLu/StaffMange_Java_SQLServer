package com.jkw;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
	private Connection dbConn;
	private Statement stateMent;
	private String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName =CompanyManage";//数据库名称需要更改
	private String userName = "sa";
	private String userPassword = "123456";
	public db(){
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(url, userName, userPassword);
			System.out.println("Connecting successfully !!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql) throws SQLException{
		stateMent = dbConn.createStatement();
		return stateMent.executeUpdate(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException{
		stateMent = dbConn.createStatement();
		return stateMent.executeQuery(sql);
	}
	
	public void closeConn() throws SQLException{
		stateMent.close();
		dbConn.close();
	}

	public PreparedStatement PreparedStatement(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return dbConn.prepareStatement(sql);
	}

	public CallableStatement prepareCall(String callProcedure) throws SQLException {
		// TODO Auto-generated method stub
		return dbConn.prepareCall(callProcedure);
	}
}
