package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.admin.DBUtil;

public class StudentConsult {

	public void requestConsulting(StudentUser studentUser) {
		
		// declare variable
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
	
		try {
			System.out.println();
			// Database connection
			conn = util.open("211.63.89.64", "project", "java1234");
			
			// insert consult request
			String sql = "{ call procAddConsultRequest(?)}";
			stat = conn.prepareCall(sql);
			
			// set param
			stat.setInt(1,studentUser.getNum());
			stat.executeUpdate();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t    상담 신청이 완료 되었습니다.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
