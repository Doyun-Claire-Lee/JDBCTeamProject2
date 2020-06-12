package com.test.student;

import java.util.Scanner;

public class StudentMain {
	
	public StudentMain() {
		// TODO Auto-generated constructor stub
	}
	public void StudentMainmenu(StudentUser sUser) {
		
		// 우리가 메뉴에서 사용할 학생 유저
		StudentUser user = sUser;
		
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓 M E N U 〓〓〓〓〓〓〓〓〓");
		System.out.println("1. 성적 조회");
		System.out.println("2. 출결 관리 및 출결 조회");
		System.out.println("3. 상담신청");
		System.out.println("4. 교사평가");
		System.out.println("5. 취업활동목록");
		System.out.println("0. 뒤로가기");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		Scanner scan = new Scanner(System.in);
		String sel = "";
		while(true) {
			// 사용자에게 번호 입력받음
			sel = scan.nextLine();
			
			// 성적 조회
			if(sel.equals("1")) {
				StudentCheckScore CheckScore = new StudentCheckScore();
			}
			// 출결 관리 및 출결 조회
			else if(sel.equals("2")) {
				StudentAttendance attendancew = new StudentAttendance();
			}
			// 상담신청
			else if(sel.equals("3")) {
				StudentConsult consulting = new StudentConsult();
			}
			// 교사 평가
			else if(sel.equals("4")) {
				Studentrating rating = new Studentrating();
			}
			// 취업 활동목록
			else if(sel.equals("5")) {
				StudentJob job = new StudentJob();
			}
			// 뒤로 가기
			else if(sel.equals("0")) {
				break;
			} 
			// 예외
			else {
				System.out.println("번호를 다시 입력해주세요.");
			}
		}
		
	}
}
