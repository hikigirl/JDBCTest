package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class Ex02_Connection {
	public static void main(String[] args) {
		//Ex02_Connection
		Connection conn = null;
		
		//연결 문자열(Connection String)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "server";
		String pw = "java1234";
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB접속
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println(conn.isClosed()); //false
			
			//sql 실행 -> conn 활용
			
			
			//DB 접속 해제
			conn.close();
			System.out.println(conn.isClosed()); //true
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
