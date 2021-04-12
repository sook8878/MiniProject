package com.mini.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {


	public static Connection getConnection() {

		// SQL과 연결하려고 만드는거임.
		Connection con = null;
		
		// 파일 뽑아내려고 만드는 것 
		Properties prop = new Properties();
		
		try {
			// 파일을 읽어들이기 위함 
			prop.load(new FileReader("config/connection-info.properties"));
			
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, prop);
			
			con.setAutoCommit(false);
			// true되어있는거 fasle로 ! 자동커밋 안함.
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		
		return con;
	} 
	
	// 프리페어드가 스테이트먼트의 자식임 클로즈는 어차피 닫는 역할이라서 스테이트먼트를 선언해주면 프리페어드까지 닫아줄 수있음.
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// void인 이유 con은 어차피 실행하고 반환할 필요가 없으니깐. 
	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/* 수동 Commit 이후*/
	
	public static void commit(Connection con) {
		try {
			
			if(con != null && !con.isClosed()) {
				con.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			
			if(con != null && !con.isClosed() ) {
				con.rollback();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

