package com.test.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.internal.OracleTypes;

public class Ex07_CallableStatement {

	public static void main(String[] args) {
		//프로시저 전용
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
	}
	
	
	private static void m5() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			String sql = "{ call procM5(?, ?) }";
			stat = conn.prepareCall(sql);
			
			stat.setString(1, "f");
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			rs = (ResultSet)stat.getObject(2); //오라클의 커서는 resultset으로 받는다
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("age"));
				System.out.println(rs.getString("gender"));
				System.out.println(rs.getString("tel"));
				System.out.println(rs.getString("address"));
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void m4() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			String sql = "{ call procM4(?, ?, ?, ?, ?, ?) }";
			stat = conn.prepareCall(sql);
			//in
			stat.setString(1, "24");
			//out
			stat.registerOutParameter(2, OracleTypes.VARCHAR);
			stat.registerOutParameter(3, OracleTypes.NUMBER);
			stat.registerOutParameter(4, OracleTypes.VARCHAR);
			stat.registerOutParameter(5, OracleTypes.VARCHAR);
			stat.registerOutParameter(6, OracleTypes.VARCHAR);
			
			stat.executeQuery();
			
			System.out.println(stat.getString(2));
			System.out.println(stat.getString(3));
			System.out.println(stat.getString(4));
			System.out.println(stat.getString(5));
			System.out.println(stat.getString(6));
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m3() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			
			//procM3(변수)
			String sql = "{ call procM3(?) }";
			stat = conn.prepareCall(sql);
			
			//out parameter
			stat.registerOutParameter(1, OracleTypes.NUMBER);
			
			stat.executeQuery(); //쿼리 실행(select문은 프로시저 내에서 역할을 다하고 out parameter만 남음)
			int count = stat.getInt(1); //pcount out number
			
			System.out.println(count);
			
			

			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m2() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			String sql = "{ call procM2(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			stat.setString(1, "닭");
			stat.setString(2, "2");
			stat.setString(3, "f");
			stat.setString(4, "010-0000-9999");
			stat.setString(5, "서울시");
			
			int result = stat.executeUpdate();
			System.out.println(result);
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m1() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			//ansi-sql 환경에서
			// call procM1 (표준)
			// execute procM1 / exec procM1(Oracle 전용)
			String sql = "{ call procM1 }";
			stat = conn.prepareCall(sql);
			
			int result = stat.executeUpdate();
			System.out.println(result);
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void m() {
		DBUtil util = new DBUtil();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		try {
			conn = util.open();
			String sql = "";
			stat = conn.prepareCall(sql);
			
			
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
