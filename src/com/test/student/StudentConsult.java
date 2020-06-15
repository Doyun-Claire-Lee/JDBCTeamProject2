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
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
	
		try {
			
			conn = util.open("211.63.89.64", "project", "java1234");
			String sql = "{ call : procAddConsultRequest(?)}";
			stat = conn.prepareCall(sql);
			
			stat.setInt(1,studentUser.getNum());
			stat.executeUpdate(sql);
			System.out.println("\t\t\t상담 신청 완료");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
