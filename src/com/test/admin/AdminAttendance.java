package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class AdminAttendance {

	public void manageAttendanceMenu() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓출결 관리〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 학생별 출결 조회");
			System.out.println("\t\t\t2. 날짜별 출결 조회");
			System.out.println("\t\t\t3. 출결 내역 조회");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷입력:");
			String num = sc.nextLine();

			if (num.equals("1")) {
				procPrintAttendanceStudent();
			} else if (num.equals("2")) {
				PROCPRINTATTENDANCEDATE();
			} else if (num.equals("3")) {
				vwAllattendance();
			} else if (num.equals("0")) {
				System.out.println("\t\t\t뒤로가는 중...");
				break;
			} else {
				System.out.println("\t\t\t잘못된 번호 입력");
			}
		}
	}
	public void procPrintAttendanceStudent() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		
		try {
			
			conn = util.open("211.63.89.64","project","java1234");
			String sql = "{ call procPrintAttendanceStudent(?,?,?) }";
			stat = conn.prepareCall(sql);
			
			System.out.print("\t\t\t학생번호:");
			int stu = scan.nextInt();
			System.out.print("\t\t\t과정번호:");
			int course = scan.nextInt();
			stat.setInt(1,stu);
			stat.setInt(2,course);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(3);
				System.out.println("\t\t\t[이름]\t[과정]\t\t[입실시각]\t[퇴실시각]\t[출결 내역]");
			while(rs.next()) {
				System.out.printf("\t\t\t%s\t%s\t\t%s\t%s\t%s\n",
													rs.getString(1), //이름
													rs.getString(2), //과정
													rs.getDate(3),   //입실시각
													rs.getDate(4),	 //퇴실시각
													rs.getString(5));//출결내역
			}
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatment.m5()");
			e.printStackTrace();
		}
		
	}

	public void vwAllattendance() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from vwAllattendance");

			rs = stat.executeQuery(sql); // select -> rs

			while (rs.next()) {
				System.out.printf("\t\t\t이름:%s 과정:%s 입실날짜:%s 퇴실날짜:%s 출결상황:%s \n"

						, rs.getString("stuName"), rs.getString("courseName"), rs.getString("enterT"),
						rs.getString("outT"), rs.getString("status"));
			}

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void PROCPRINTATTENDANCEDATE() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			System.out.print("\t\t\t날짜입력(YY):");
			String year = scan.nextLine();
			System.out.print("\t\t\t날짜입력(MM):");
			String month = scan.nextLine();
			System.out.print("\t\t\t날짜입력(DD):");
			String date = scan.nextLine();
			String sql = "{call PROCPRINTATTENDANCEDATE(?,?,?,?)}";

			stat = conn.prepareCall(sql);

			stat.setString(1, year);
			stat.setString(2, month);
			stat.setString(3, date);
			stat.registerOutParameter(4, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(4);

			while (rs.next()) {
				System.out.printf("\t\t\t%s-%s-%s-%s-%s\n", rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5));

			}

		} catch (Exception e) {
			System.out.println("Ex07_CallableStatment.m5()");
			e.printStackTrace();
		}

	}

}
