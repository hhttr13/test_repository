package com.spring.myapp.mysqltest;

import java.sql.*;

import org.junit.Test;

public class MySqlConnectTest {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/spring?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String UID = "root";
	private static final String UPW = "mysql";
	
	@Test
	public void connectTest() throws Exception {
		Class.forName(DRIVER);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, UID, UPW);
			System.out.println(conn);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}
	
}











