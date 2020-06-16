package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

public class StudentJob {

	public void procPrintAttendanceStudent() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {

			conn = util.open("211.63.89.64", "project", "java1234");
			String sql = "{ call procPrintAttendanceStudent(?,?,?) }";
			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t학생번호:");
			int stu = scan.nextInt();
			System.out.print("\t\t\t과정번호:");
			int course = scan.nextInt();
			stat.setInt(1, stu);
			stat.setInt(2, course);
			stat.registerOutParameter(3, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(3);
			System.out.println("\t\t\t[이름]\t[과정]\t\t[입실시각]\t[퇴실시각]\t[출결 내역]");
			while (rs.next()) {
				System.out.printf("\t\t\t%s\t%s\t\t%s\t%s\t%s\n", rs.getString(1), // 이름
						rs.getString(2), // 과정
						rs.getDate(3), // 입실시각
						rs.getDate(4), // 퇴실시각
						rs.getString(5));// 출결내역
			}

		} catch (Exception e) {
			System.out.println("Ex07_CallableStatment.m5()");
			e.printStackTrace();
		}
	}
}
