package com.test.teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.test.admin.AdminMain;
import com.test.admin.AdminUser;
import com.test.admin.DBUtil;
import com.test.student.StudentMain;

public class TeacherUser {

	int num;
	String name;
	String tel;
	String ssn;

	public void login(TeacherUser teacherUser) {

		// Database connection
		// 데이터베이스 연동
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		// Variable for teacher Info
		// 관리자 계정 데이터를 넣어줄 변수
		HashMap<String, ArrayList<String>> teacherInfo = new HashMap<String, ArrayList<String>>();

		try {
			conn = util.open("localhost", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from tblTeacher");
			rs = stat.executeQuery(sql);

			// Insert info to loginInfo map
			// 데이터 입력
			while (rs.next()) {
				ArrayList<String> temp = new ArrayList<String>();

				// only used data
				if (rs.getString("deleteStatus").equals("0")) {
					// ssn, num, name
					temp.add(rs.getString("ssn"));
					temp.add(rs.getString("num"));
					temp.add(rs.getString("name"));
					teacherInfo.put(rs.getString("tel"), temp);
				}

			}

			// input id, pw
			System.out.print("▷ ID: \n");
			String inputId = scan.nextLine();
			System.out.println(inputId);
			System.out.print("▷ PW: \n");
			String inputPw = scan.nextLine();

			// iterator
			Iterator<String> keys = teacherInfo.keySet().iterator();

			// loginInfo search
			for (String id : teacherInfo.keySet()) {
				// id matching
				if (id.equals(inputId)) {

					// password get
					String ssn = teacherInfo.get(id).get(0);
					String pw = ssn.substring(ssn.indexOf('-') + 1);

					// pw matching
					if (pw.equals(inputPw)) {
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.printf("아이디 : %s\n", id);
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓");

						TeacherMain teacherMain = new TeacherMain();
						TeacherUser tUser = teacherUser;
						
						// set info
						tUser.setSsn(teacherInfo.get(id).get(0));
						tUser.setNum(Integer.parseInt(teacherInfo.get(id).get(1)));
						tUser.setName(teacherInfo.get(id).get(2));
						tUser.setTel(id);

						teacherMain.TeacherMainmenu(tUser);
					}

				}

			}
			System.out.println("아이디와 비밀번호를 다시 입력해주세요");

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
