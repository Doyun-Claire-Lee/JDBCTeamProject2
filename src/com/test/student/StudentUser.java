package com.test.student;

public class StudentUser {

	public StudentUser() {
		// TODO Auto-generated constructor stub
	}

	public void login(StudentUser sUser) {

		// 로그인 성공시
		StudentMain sMain = new StudentMain(); // sUser 전달
		sMain.StudentMainmenu(sUser);
	}
}
