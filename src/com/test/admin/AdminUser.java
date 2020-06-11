package com.test.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminUser {

	public void login(AdminUser adminmain) {


		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		System.out.println("connection complete");
		
		ArrayList<String> ID = new ArrayList<String>();
		ArrayList<String> PSW = new ArrayList<String>();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from tbladmin");
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				
				// 로그인 성
				AdminUser adminUser = adminmain;
				AdminMain Auser = new AdminMain();
				 Auser.menu();

			}

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
