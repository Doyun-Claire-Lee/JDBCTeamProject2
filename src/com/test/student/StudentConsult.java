package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.admin.DBUtil;

public class StudentConsult {
	public static void main(String[] args) {
		StudentConsult c1 = new StudentConsult();
		StudentUser user = new StudentUser();
		user.setNum(2);
		c1.requestConsulting(user);
	}

	public void requestConsulting(StudentUser studentUser) {
		
		// declare variable
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
	
		try {
			
			// Database connection
			conn = util.open("211.63.89.64", "project", "java1234");
			
			// insert consult request
			String sql = "{ call procAddConsultRequest(?)}";
			stat = conn.prepareCall(sql);
			
			// set param
			stat.setInt(1,studentUser.getNum());
			stat.executeUpdate();
			System.out.println("\t\t\t상담 신청 완료");
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
