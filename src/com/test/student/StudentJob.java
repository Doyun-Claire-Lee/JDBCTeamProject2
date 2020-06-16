package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

public class StudentJob {
	
	public void procStudentjobActivity(StudentUser user) {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t취업활동");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 내 취업활동 내역 확인");
			System.out.println("\t\t\t2. 취업 활동 내역 입력");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷입력: ");
			Scanner sc = new Scanner(System.in);
			String cho = sc.nextLine();
			if (cho.equals("1")) { // 취업 활동 내역 확인
				procprintsupportlist(user.getNum());
			} else if (cho.equals("2")) { // 취업 활동 내역 입력
				procAddJobActivity(user.getNum());
			} else if (cho.equals("0")) {
				break;
			}
		}
	}
	public void procprintsupportlist(int i) {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64","project","java1234");
			String sql = "{ call procprintsupportlist(?,?)}";
			
			conn.setAutoCommit(false);
			stat = conn.prepareCall(sql);

			int snum = i;
		    stat.registerOutParameter(1, OracleTypes.CURSOR);
		    stat.setInt(2, snum);
		    
		    stat.executeQuery();
		    rs =(ResultSet)stat.getObject(1);
		    System.out.println("\t\t\t[학생명]\t[학생 번호]\t[취업 지원 내용]");
		    while(rs.next()) {
		    	System.out.printf("\t\t\t%s\t%s\t%s \n",
		    							rs.getString(1)
		    							,rs.getString(2)
		    							,rs.getString(3));
		    }
		    
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} 
	}

	public void procAddJobActivity(int i) {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procAddJobActivity(?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t취업활동 내용 입력:");
			String content = sc.nextLine();

			int name = i;
			System.out.println(i);
			stat.setInt(1, name);
			stat.setString(2, content);
			stat.registerOutParameter(3, OracleTypes.NUMBER);

			int result = stat.getInt(3);
			stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t취업활동 등록 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
