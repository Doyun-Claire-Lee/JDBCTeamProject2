package com.test.teacher;

import java.util.Scanner;

public class TeacherMain {
	
	

	public void TeacherMainmenu(TeacherUser teacherUser) {
		
		TeacherUser user = teacherUser;
		
		Scanner scan = new Scanner(System.in);
		String sel = "";
		
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓 M E N U 〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 강의 스케줄 조회");
			System.out.println("2. 배점 입출력");
			System.out.println("3. 성적 입출력");
			System.out.println("4. 출결 관리 및 출결 조회");
			System.out.println("5. 교사 평가 조회");
			System.out.println("6. 성적 우수자 / 보강수업 대상자 조회");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력: \n");
			// 사용자에게 번호 입력받음
			sel = scan.nextLine();

			// 강의 스케줄 조회
			if (sel.equals("1")) {
				TeacherCheckSchedule checkSchedule = new TeacherCheckSchedule();
			}
			// 배점 입출력
			else if (sel.equals("2")) {
				TeacherInsertRatio ratio = new TeacherInsertRatio();
			}
			// 성적 입출력
			else if (sel.equals("3")) {
				TeacherInsertRatio score = new TeacherInsertRatio();
				score.main(user);
			}
			// 출결 관리 및 출결 조회
			else if (sel.equals("4")) {
				TeacherAttendance attendance = new TeacherAttendance();
			}
			// 교사 평가 조회
			else if (sel.equals("5")) {
				TeacherCheckRating rating = new TeacherCheckRating();
			} 
			// 성적 우수자 / 보강수업 대상자 조회
			else if (sel.equals("6")) {
				TeacherCheckOutstander outstand = new TeacherCheckOutstander();
			} 
			// 뒤로 가기
			else if (sel.equals("0")) {
				break;
			} 
			// 예외
			else {
				System.out.println("번호를 다시 입력해주세요");
			}
		}
	}

}
