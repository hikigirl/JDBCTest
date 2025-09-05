package com.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex04_Statement {

	public static void main(String[] args) {
		//Statement 종류
		//1. Statement
		//2. PreparedStatement
		//3. CallableStatement
		
		//m1();
		//m2();
		//m3();
		//m4();
		//m5();
		m6();
	}

	private static void m6() {
		//사용자가 입력받은 데이터를 db와 연동하기
		Scanner scan = new Scanner(System.in);
		
		//서로 다른 언어(환경)에서는 자료형이 호환되지 않는다.
		System.out.println("정보를 입력하세요.");
		System.out.print("이름: ");
		String name = scan.nextLine(); //varchar2 != String
		
		System.out.print("나이: ");
		String age = scan.nextLine(); //number != int
		
		System.out.print("성별: ");
		String gender = scan.nextLine();
		
		System.out.print("전화번호: ");
		String tel = scan.nextLine();
		
		System.out.print("주소: ");
		String address = scan.nextLine();
		
		String sql = String.format("insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
		System.out.println(sql);
		
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			System.out.println(result);
			
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
		
		
	}

	private static void m5() {
		//반환값이 없는 쿼리.. DML(insert, update, delete), DDL(create, drop, alter), DCL(commit,rollback)
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;

		try {
			conn = util.open();
			stat = conn.createStatement();
			String sql = "";
			
//			sql = "create table tblAddress2 (\r\n"
//					+ "    seq number primary key,\r\n"
//					+ "    name varchar2(30) not null,\r\n"
//					+ "    age number(3) not null check(age between 0 and 120),\r\n"
//					+ "    gender char(1) not null check(gender in ('m', 'f')),\r\n"
//					+ "    tel varchar2(15) not null,\r\n"
//					+ "    address varchar2(300) not null,\r\n"
//					+ "    regdate date default sysdate not null\r\n"
//					+ ")";
			sql = "drop table tblAddress2";
			
			int result = stat.executeUpdate(sql);
			System.out.println(result); //update, insert, delete시에만 0 아닌 값도 나올 수 있음. DDL 등은 0이라고 나옴
			
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
	}
	
	private static void m4() {
		//delete문
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;

		try {
			conn = util.open();
			stat = conn.createStatement();
			String sql = "";
			
			sql = "delete from tblAddress where seq=1";
			int result = stat.executeUpdate(sql);
			System.out.println(result);
			
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
	}
	
	private static void m3() {
		//update문
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;

		try {
			conn = util.open();
			stat = conn.createStatement();
			String sql = "";
			
			sql = "update tblAddress set age = age + 1";
			int result = stat.executeUpdate(sql);
			System.out.println(result);
			
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close();
		}
	}

	private static void m2() {
		//JDBC 기본 동작 -> 자동 커밋(executeUpdate())
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = util.open(); //접속
			
			stat = conn.createStatement();
			if (!conn.isClosed()) {

				String sql = "insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '고양이', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default)";
				
				int result = stat.executeUpdate(sql);
				System.out.println(result);
				if(result>0) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
				
				
				Scanner scan = new Scanner(System.in);
				scan.nextLine(); //일시정지를 위해 스캐너
				
//				conn.commit();
				conn.rollback();
				
				stat.close();
			} else {
				System.out.println("DB 연결 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.close(); //접속 해제
		}
		
	}
	
	private static void m1() {
		//insert문
		DBUtil util = new DBUtil();
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = util.open(); //접속
			
			stat = conn.createStatement();
			if (!conn.isClosed()) {
//				SQL 실행 -> 자바는 SQL을 해석하지 못한다 -> 문자열로 취급, 의미를 갖지 않음
//				 코드 상에서는 SQL의 오류를 발견하지 못한다. -> 정적 체크
//				 실제 실행을 해야 발견 가능
				
				//java.sql.SQLSyntaxErrorException: ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다
				//Statement는 한번에 딱 한개의 SQL만 실행 가능하다. 세미콜론을 빼야함...
				String sql = "insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default)";
				
				int result = stat.executeUpdate(sql); //sql이 서버로 전송
				System.out.println(result);
				if(result>0) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
				
				stat.close(); //stat은 생성될 때 열린다. 꼭 닫아줘야함
				
			} else {
				System.out.println("DB 연결 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(); //접속 해제
		}
		
	}

}
