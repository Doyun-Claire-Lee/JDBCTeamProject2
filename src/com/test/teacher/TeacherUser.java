package com.test.teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.admin.DBUtil;
import com.test.student.StudentMain;

public class TeacherUser {
	
	int num;
	String name;
	String tel;
	String ssn;
	
	public void login(TeacherUser teacherUser) {
		// TODO Auto-generated method stub
		
		// 데이터베이스 연동
		
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

			String sql = String.format("select * from tblTeacher");
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
			TeacherMain tMain = new TeacherMain(); // sUser 전달
			// setter로 adminUser에 데이터 넣기

			// 메인 메뉴에 데이터가 들어가있는 adminUser 객체를 넣어줌
			TeacherUser tUser = teacherUser;
			tMain.TeacherMainmenu(tUser);

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 로그인 성공시
		
		TeacherMain tMain = new TeacherMain();
		// setter
		tMain.TeacherMainmenu(teacherUser);
	}

	// getter & setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


}
