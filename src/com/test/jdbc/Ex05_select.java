package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex05_select {

	public static void main(String[] args) {
		//Ex05_select
		//m1();
		//m2();
		//m3();
		//m4();
		//m5();
		m6();
	}
	
	
	private static void m6() {
		/*
		 * hr.tblInsa
		 * 1. 어떤 부서들이 있는지 출력 -> 7개
		 * 2. 사용자 -> 부서명 1개를 입력
		 * 3. 해당 부서의 직원 목록 출력(num, name, jikwi, basicpay)
		 */
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = util.open("localhost", "hr", "java1234");
//			System.out.println(conn.isClosed()); //연결 확인
			
			stat = conn.createStatement(); //연결 정보와 쿼리를 연동
			
			sql = "SELECT DISTINCT buseo FROM TBLINSA";
			rs = stat.executeQuery(sql);
			
			ArrayList<String> buseoList = new ArrayList<String>();
			//String buseo = "";
			int num = 0;
			String name = "";
			String jikwi = "";
			int basicpay = 0;
			
			//부서명 출력
			System.out.println("[부서 목록]");
			while (rs.next()) { 
//				buseo = rs.getString("buseo");
				buseoList.add(rs.getString("buseo"));
//				System.out.println(buseo);
//				System.out.println(buseoList.);
			}
			for (int i=0; i<buseoList.size();i++) {
				System.out.println(buseoList.get(i));
			}
			
			
			System.out.println("==========");
			System.out.println("조회를 원하는 부서명을 입력하세요.");
			System.out.print("부서명: ");
			String userBuseo = scan.nextLine();
			
			if (!buseoList.contains(userBuseo)) {
				System.out.println("부서명이 잘못되었습니다. 다시 입력하세요.");
				while (true) {
					
					System.out.println("조회를 원하는 부서명을 입력하세요.");
					System.out.print("부서명: ");
					userBuseo = scan.nextLine();
					if (buseoList.contains(userBuseo)) { break; }
				}
			}
			
			if (buseoList.contains(userBuseo)) {
				
				//System.out.println(userBuseo);
				
				sql = String.format("SELECT num, name, jikwi, basicpay FROM TBLINSA WHERE BUSEO='%s'", userBuseo);
//				System.out.println(sql);
				rs = stat.executeQuery(sql);
				System.out.println("[번호]\t[이름]\t[직위]\t[급여]");
				while (rs.next()) {
					num = rs.getInt("num");
					name = rs.getString("name");
					jikwi = rs.getString("jikwi");
					basicpay = rs.getInt("basicpay");
					System.out.printf("%d\t%s\t%s\t%,d원\n", num, name, jikwi, basicpay);
				}
			}
			
			
			
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	private static void m5() {
		//다중값 - n행 n열
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			String sql = "SELECT * FROM tblAddress";
			rs = stat.executeQuery(sql);
			
			String name = "";
			int age = 0;
			String address = "";
			
			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				address = rs.getString("address");
				System.out.printf("%s(%d세) %s\n", name, age, address);
			}
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m4() {
		//다중값 - n행 1열
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			String sql = "SELECT name FROM TBLADDRESS";
			rs = stat.executeQuery(sql);
			
			String name ="";
			
			while (rs.next()) {
				name = rs.getString("name");
				System.out.println(name);				
			}
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m3() {
		//다중값 - 1행 n열
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			String sql = "SELECT * FROM TBLADDRESS WHERE seq = 24";
//			String sql = "SELECT * FROM TBLADDRESS WHERE saq = 24"; //ORA-00904: "SAQ": 부적합한 식별자 -> 오라클 에러
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
//				String name = rs.getString("name");
				String name = rs.getString("nam"); //java.sql.SQLException: 부적합한 열 이름 -> 자바 에러
				int age = rs.getInt("age"); 
				String address = rs.getString("address");
				System.out.println(name + ", " + age + ", " + address);
			} else {
				System.out.println("결과가 없습니다.");
			}
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m2() {
		//단일값 - 1행 1열
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("번호(24,41,81): ");
		String seq = scan.nextLine();
		
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			//사용자가 원하는 사람의 이름 -> 조건(seq)
			String sql = "select name from tblAddress where seq=" + seq;
			rs = stat.executeQuery(sql);
			
			//boolean java.sql.ResultSet.next();
			//유효한 레코드가 있는지 확인. true면 레코드가 있다는 것
			if(rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			} else {
				System.out.println(seq+"번이 없습니다.");
			}
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m1() {
		//단일값을 가져오는 select문
		//1행 1열
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			String sql = "select count(*) as count from tblAddress";
			rs = stat.executeQuery(sql);
			rs.next(); //iterator를 이동(1행 1열이 확정이라서 레코드 유무 체크를 안하고 넘어간거임)
			//int count = rs.getInt(1);
			//int count = rs.getInt("count");
			
			//형변환하는 대신 가져올 때 원하는 자료형을 정하게끔 메서드가 분리되어있음
			String count = rs.getString("cnt"); 
			System.out.println(count);
			
			
//			System.out.println();
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			String sql = "";
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
