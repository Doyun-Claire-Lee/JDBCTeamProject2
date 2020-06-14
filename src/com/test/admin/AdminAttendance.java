package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class AdminAttendance {

	public void menu() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓출결 관리〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 학생별 출결 조회");
			System.out.println("2. 날짜별 출결 조회");
			System.out.println("3. 출결 내역 조회");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			String num = sc.nextLine();

			if (num.equals("1")) {
				vwAllattendance();
			} else if (num.equals("2")) {
				PROCPRINTATTENDANCEDATE();
			} else if (num.equals("3")) {
				vwAllattendance();
			} else if (num.equals("0")) {
				System.out.println("뒤로가는 중...");
				break;
			} else {
				System.out.println("잘못된 번호 입력");
			}
		}
	}

	public void vwAllattendance() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("localhost", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from vwAllattendance");

			rs = stat.executeQuery(sql); // select -> rs

			while (rs.next()) {
				System.out.printf("이름:%s 과정:%s 입실날짜:%s 퇴실날짜:%s 출결상황:%s \n"

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
			conn = util.open("localhost", "project", "java1234");

			System.out.print("날짜입력(YY):");
			String year = scan.nextLine();
			System.out.print("날짜입력(MM):");
			String month = scan.nextLine();
			System.out.print("날짜입력(DD):");
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
				System.out.printf("%s-%s-%s-%s-%s\n", rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5));

			}

		} catch (Exception e) {
			System.out.println("Ex07_CallableStatment.m5()");
			e.printStackTrace();
		}

	}

}
