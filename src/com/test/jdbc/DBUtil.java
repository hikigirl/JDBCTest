package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private Connection conn;
	
	//DB 접속 작업
	public Connection open() {
		//연결 문자열(Connection String)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "server";
		String pw = "java1234";
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB접속
			conn = DriverManager.getConnection(url, id, pw);
	
			//연결 직후 ~ 트랜잭션 설정 ~ SQL 실행
			//conn.setAutoCommit(false);
			
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null; //연결 실패시
		}
	}
	
	public Connection open(String server, String id, String pw) {
		//연결 문자열(Connection String)
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB접속
			conn = DriverManager.getConnection(url, id, pw);
	
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null; //연결 실패시
		}
	}
	
	
	//DB 접속 끊는 작업
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		


}
