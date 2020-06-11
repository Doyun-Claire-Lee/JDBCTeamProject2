package com.test.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminUser {

	public void login(AdminUser adminmain) {

		System.out.println("관리자 로그인");

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		ArrayList<String> ID = new ArrayList<String>();
		ArrayList<String> PSW = new ArrayList<String>();

		try {
			conn = util.open();
			stat = conn.createStatement();

			String sql = String.format("select ID, PSW from tbladmin");

			while (rs.next()) {

				// 로그인 성고시
				AdminUser adminUser = adminmain;
				AdminMain Auser = new AdminMain();
				// Auser.menu();

			}

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
