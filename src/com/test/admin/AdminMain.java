package com.test.admin;

import java.util.Scanner;

public class AdminMain {

	public void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================");
		System.out.println("\t교육센터 관리 시스템");
		System.out.println("============================================");
		
		
		System.out.println("1.기초 정보 관리");
		System.out.println("2.교사 계정 관리");
		System.out.println("3.개설 과정 관리");
		System.out.println("4.개설 과목 관리");
		System.out.println("5.교육생 관리");
		System.out.println("6.시험 관리");
		System.out.println("7.출결 관리");
		System.out.println("8.기자재 관리");
		
		System.out.println("============================================");
		System.out.print("▷입력:");
		int cho = sc.nextInt();
		if(cho == 1) {
			AdminBasic basic = new AdminBasic();
			
		}
		else if(cho == 2) {
			AdminTeacher teacher = new AdminTeacher();
			
		}
		else if(cho == 3) {
			AdminOpencourse opencourse = new AdminOpencourse();
		}
		else if(cho == 4) {
			AdminSubject subject = new AdminSubject();
			
		}
		else if(cho == 5) {
			AdminStudent student = new AdminStudent();
		
		}
		else if(cho == 6) {
			AdminExam exam = new AdminExam();
		}
		else if(cho == 7) {
			AdminAttendanceM attendance = new AdminAttendanceM();
		}
		else if(cho == 8) {
			AdminEquipment equipment = new AdminEquipment();
		}
		
	}
}
