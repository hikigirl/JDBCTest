package com.test.jdbc;

import java.sql.Connection;

public class Ex03_Connection {

	public static void main(String[] args) {
		// DB접속(미리 만들어둔 DBUtil 클래스 사용해서)
		DBUtil util = new DBUtil();
		Connection conn = null;
		
		
		try {
			conn = util.open();
			
			System.out.println(conn.isClosed()); //false
			util.close();
			System.out.println(conn.isClosed()); //true
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
