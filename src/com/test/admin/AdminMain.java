package com.test.admin;

import java.util.Scanner;

public class AdminMain {

	public void mainmenu(AdminUser adminUser) {

		AdminUser user = adminUser;


		Scanner scan = new Scanner(System.in);
		String sel = "";

		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓 M E N U 〓〓〓〓〓〓〓〓〓");
			System.out.println("1.기초 정보 관리");
			System.out.println("2.교사 계정 관리");
			System.out.println("3.개설 과정 관리");
			System.out.println("4.개설 과목 관리");
			System.out.println("5.교육생 관리");
			System.out.println("6.시험 관리");
			System.out.println("7.출결 관리");
			System.out.println("8.기자재 관리");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력:");
			
			// 사용자에게 번호 입력받음
			sel = scan.nextLine();

			// 기초 정보 관리
			if (sel.equals("1")) {
				AdminBasic basic = new AdminBasic();
				basic.menu();
			}
			// 교사 계정 관리
			else if (sel.equals("2")) {
				AdminTeacher teacher = new AdminTeacher();
				teacher.menu();
			}
			// 개설 과정 관리
			else if (sel.equals("3")) {
				AdminOpencourse opencourse = new AdminOpencourse();
			}
			// 개설 과목 관리
			else if (sel.equals("4")) {
				AdminSubject subject = new AdminSubject();
				subject.menu();
			}
			// 교육생 관리
			else if (sel.equals("5")) {
				AdminStudent student = new AdminStudent();
				student.menu();
			}
			// 시험 관리
			else if (sel.equals("6")) {
				AdminExam exam = new AdminExam();
			}
			// 출결 관리
			else if (sel.equals("7")) {
				AdminAttendance attendance = new AdminAttendance();
				attendance.menu();
			}
			// 기자재 관리
			else if (sel.equals("8")) {
				AdminEquipment equipment = new AdminEquipment();
				equipment.menu();
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
