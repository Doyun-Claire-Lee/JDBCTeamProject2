package com.test.teacher;

public class TeacherUser {

	public void login(TeacherUser tUser) {
		// TODO Auto-generated method stub
		TeacherUser teacherUser = tUser;
		// 로그인 성공시
		
		TeacherMain tMain = new TeacherMain();
		tMain.TeacherMainmenu(teacherUser);
	}

}
