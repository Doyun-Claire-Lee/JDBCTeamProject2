package com.test.teacher;

public class TeacherUser {

	public void login(TeacherUser tUser) {
		// TODO Auto-generated method stub
		TeacherUser teacherUser = tUser;
		
		// 데이터베이스 연동해서
		// 저 위에 유저에 데이터 넣어주면
		// 로그인 성공시
		
		TeacherMain tMain = new TeacherMain();
		// setter
		tMain.TeacherMainmenu(teacherUser);
	}

}
