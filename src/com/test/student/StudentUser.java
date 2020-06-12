package com.test.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.test.admin.AdminMain;
import com.test.admin.AdminUser;
import com.test.admin.DBUtil;
import com.test.teacher.TeacherMain;
import com.test.teacher.TeacherUser;

public class StudentUser {

	int num;
	String name;
	String tel;
	String ssn;
	String registerDate; // 의논 필요

	public void login(StudentUser studentUser) {

		// Database connection
		// 데이터베이스 연동
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		// Variable for student Info
		// 관리자 계정 데이터를 넣어줄 변수
		HashMap<String, ArrayList<String>> studentInfo = new HashMap<String, ArrayList<String>>();

		try {

			conn = util.open("localhost", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from tblStudent");
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
					temp.add(rs.getString("registerdate"));
					studentInfo.put(rs.getString("tel"), temp);
				}

			}

			// input id, pw
			System.out.print("▷ ID: \n");
			String inputId = scan.nextLine();
			System.out.println(inputId);
			System.out.print("▷ PW: \n");
			String inputPw = scan.nextLine();

			// iterator
			Iterator<String> keys = studentInfo.keySet().iterator();

			// loginInfo search
			for (String id : studentInfo.keySet()) {
				// id matching
				if (id.equals(inputId)) {

					// password get
					String ssn = studentInfo.get(id).get(0);
					String pw = ssn.substring(ssn.indexOf('-') + 1);

					// pw matching
					if (pw.equals(inputPw)) {
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.printf("아이디 : %s\n", id);
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓");

						StudentMain studentMain = new StudentMain();
						StudentUser sUser = studentUser;

						// set info
						sUser.setSsn(studentInfo.get(id).get(0));
						sUser.setNum(Integer.parseInt(studentInfo.get(id).get(1)));
						sUser.setName(studentInfo.get(id).get(2));
						sUser.setRegisterDate(studentInfo.get(id).get(3));
						sUser.setTel(id);

						studentMain.StudentMainmenu(sUser);
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public StudentUser() {
		// TODO Auto-generated constructor stub
	}

}
