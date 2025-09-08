package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ex06_PreparedStatement {

	public static void main(String[] args) {
		//m1();
		m2();

		
	}

	private static void m2() {
//		insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default);
		
		String name = "꿀꿀이";
		String age = "5";
//		int age = 5;
		String gender = "m";
		String tel = "010-1234-5678";
		String address = "서울시 강남구 역삼동 한독's 빌딩";
		
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		try {
			conn = util.open();
			
			
			
			//2. PreparedStatement
			// - SQL의 데이터(값)
			
			String sql = "insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, ?, ?, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, name);
			pstat.setString(2, age);
//			pstat.setInt(2, age);
			pstat.setString(3, gender);
			pstat.setString(4, tel);
			pstat.setString(5, address);
			
			int result = pstat.executeUpdate();
			System.out.println(result);
			pstat.close();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m1() {
//		insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default);
		
		String name = "꿀꿀이";
		String age = "5";
//		int age = 5;
		String gender = "m";
		String tel = "010-1234-5678";
		String address = "서울시 강남구 역삼동 한독's 빌딩";
		
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		try {
			conn = util.open();
			
			//1. Statement
			stat = conn.createStatement();
			String sql = String.format("insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
			
			int result = stat.executeUpdate(sql);
			System.out.println(result);
			
			stat.close();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
