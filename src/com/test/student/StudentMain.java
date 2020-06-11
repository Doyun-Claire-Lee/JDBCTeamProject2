package com.test.student;

import java.util.Scanner;

public class StudentMain {
	Scanner scan;
	int sel;
	public StudentMain() {
		// TODO Auto-generated constructor stub
	}
	public void StudentMainmenu(StudentUser sUser) {
		
		// 우리가 메뉴에서 사용할 학생 유저
		StudentUser user = sUser;
		
		System.out.println("1. 성적 조회");
		System.out.println("2. 출결 관리 및 출결 조회");
		System.out.println("3. 상담신청");
		System.out.println("4. 교사평가");
		System.out.println("5. 취업활동목록");
		System.out.println("0. 뒤로가기");
		
		while(true) {
			// 사용자에게 번호 입력받음
			sel = scan.nextInt();
			
			// 성적 조회
			if(sel == 1) {
				// 여기ㅇ
				StudentCheckScore CheckScore = new StudentCheckScore();
			}
			// 출결 관리 및 출결 조회
			else if(sel == 2) {
				StudentAttendance attendancew = new StudentAttendance();
			}
			// 상담신청
			else if(sel == 3) {
				StudentConsult consulting = new StudentConsult();
			}
			// 교사 평가
			else if(sel == 4) {
				Studentrating rating = new Studentrating();
			}
			// 취업 활동목록
			else if(sel == 5) {
				StudentJob job = new StudentJob();
			}
			else if(sel == 0) {
				System.out.println("번호를 다시 입력해주세요");
			}
		}
		
	}
}
