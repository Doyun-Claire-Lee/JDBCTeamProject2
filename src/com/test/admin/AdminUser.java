package com.test.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminUser {
	
	int num;
	String id;
	String pw;


	public void login(AdminUser adminUser) {


		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		// 사용자에게 ID,PW 입력받기
		
		// 데이터베이스에서 가져올 id,pw 데이터를 넣을 변수
		ArrayList<String> ID = new ArrayList<String>();
		ArrayList<String> PSW = new ArrayList<String>();
		
		try {
			
			conn = util.open("localhost", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from tbladmin");
			rs = stat.executeQuery(sql);
			
			// 아이디 넣고
			while (rs.next()) {
				System.out.println("test");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));

			}
			System.out.println("connection complete");
			// 검사
			
			// 로그인 성공시 메인메뉴 생성
			AdminMain Auser = new AdminMain();
			// setter로 adminUser에 데이터 넣기
			AdminUser aUser = adminUser;
			
			
			// 메인 메뉴에 데이터가 들어가있는 adminUser 객체를 넣어줌
			Auser.mainmenu(adminUser);

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// getter & setter
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
}
