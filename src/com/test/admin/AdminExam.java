package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class AdminExam {
	
	public static void main(String[] args) {
		
		AdminExam exam = new AdminExam();
		exam.menu();
		
		
	}//main
	
	public void menu() {
		
		Scanner scan = new Scanner(System.in);
		
		
		while (true) {

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t    시험 관리 및 성적 조회");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	
			System.out.println("\t\t\t1. 시험 성적 등록 내역 조회");
			System.out.println("\t\t\t2. 시험 문제 등록 내역 조회");
			System.out.println("\t\t\t3. 개설 과목별 성적 조회");
			System.out.println("\t\t\t4. 교육생 개인별 성적 조회");
			System.out.println("\t\t\t0. 뒤로가기");
	
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷ 입력:");
			String mnum = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
		
			
			if (mnum.equals("1")) {

				//시험 성적 등록 내역 조회
				
			} else if (mnum.equals("2")) {

				//시험 문제 등록 내역 조회
				
			} else if (mnum.equals("3")) {
				
				procScoreInfoBySubject();
				//개설 과목별 성적 조회
					
			} else if (mnum.equals("4")) {
				
				procScoreInfoByStudent();
				//교육생 개인별 성적 조회
				
			} else if (mnum.equals("0")) {
				//뒤로가기
				System.out.println("\t\t\t뒤로가기를 선택하셨습니다.");
				System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
				scan.nextLine();
				break;
				
			} else {
				// 메인메뉴 유효성 검사
				System.out.println();
				System.out.println("\t\t\t잘못 입력하셨습니다. ");
				System.out.println("\t\t\t1에서 4 사이의 숫자를 입력해주세요.");
				System.out.println();
			}
		}//while

	}//mainMenu()

	
	
	
	
	
	//3. 개설 과목별 성적 조회
	
	private static void procScoreInfoBySubject() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		String snum = procScoreBasicInfoBySubject();
		
		try {

			conn = util.open("211.63.89.64", "project" ,"java1234");
			String sql = "{ call procScoreInfoBySubject(?,?,?)";
			
			stat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.println("\t\t\t상세 내역을 조회할 과정 번호를 입력하세요");
			

			String ocnum = "";
			System.out.print("\t\t\t▷ 과정 번호 입력 : "); //과정번호입력
			ocnum = scan.nextLine();
			System.out.println();
			
			stat.setString(1, snum);
			stat.setString(2, ocnum);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(3);
			
			
			System.out.println();
			System.out.println("\t       [개설과목명]    [교육생 이름]  [주민번호]    [필기점수]  [실기점수]");
			System.out.println("\t       ――――――――――――――――――――――――――――――――――――――――――――――――――――");
			
			while (rs.next()) {
				System.out.printf("\t\t%-15s%13s%17s%9s%9s\n"
						, rs.getString("subjectname")
						, rs.getString("studentname")
						, rs.getString("studentssn")
						, rs.getString("writtentestscore")
						, rs.getString("performancetestscore"));
			}
			
			stat.close();
			conn.close();
						

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//뒤로가기 전 pause
		System.out.println();
		System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
		scan.nextLine();
		
		
	}//procScoreInfoBySubject()



	private static String procScoreBasicInfoBySubject() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		
		String snum = "";

		try {

			conn = util.open("211.63.89.64", "project" ,"java1234");
			String sql = "{ call procScoreBasicInfoBySubject(?,?)";
			
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t     개설 과목별 성적 조회 ");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			

			System.out.print("\t\t\t▷ 과목 번호 입력 : "); //과목번호입력
			snum = scan.nextLine();			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.setString(1, snum);
			
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			
			System.out.println();
			System.out.println("[과정번호]    [과목명]     [과정시작일]       [과정종료일]       [강의실명]   [교사명]   [교재명]         [개설과정명] ");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			
			while (rs.next()) {
				System.out.printf("%-8s  %-16s%-15s%-15s%7s\t%5s\t%-15s\t%-30s\n"
						, rs.getString("opencoursenum")		
						, rs.getString("subjectname")
						, rs.getString("startdate")
						, rs.getString("enddate")
						, rs.getString("classromname") + "강의실"
						, rs.getString("teachername")
						, rs.getString("bookname")
						, rs.getString("coursename"));
			}
			
			stat.close();
			conn.close();
						
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return snum;
	}
	
	
	//4. 교육생 개인별 성적 조회
	
	private static void procScoreInfoByStudent() {
		
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		String snum = procScoreInfoTnameByStudent();

		try {
			

			conn = util.open("211.63.89.64", "project" ,"java1234");
			String sql = "{ call procScoreInfoByStudent(?,?)";
			
			stat = conn.prepareCall(sql);
			
			//실행 전 pause
			System.out.println();
			System.out.println();
			System.out.println("\t\t          엔터를 입력하시면 해당 교육생의 수강 과목별 성적이 조회됩니다.");
			scan.nextLine();
			
			//처음 입력한 교육생 번호
			stat.setString(1, snum);
	
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			System.out.println();
			System.out.println();
			System.out.println("\t        [개설과목명]               [출결점수]   [필기점수]   [실기점수]");
			System.out.println("\t        ――――――――――――――――――――――――――――――――――――――――――――――――――――");
			
			while (rs.next()) {
				System.out.printf("\t\t%-15s\t         %-11s%-11s%-11s\n"
						, rs.getString("subjectname")
						, rs.getString("attendancescore")
						, rs.getString("writtentestscore")
						, rs.getString("performancetestscore"));
			}

			
			stat.close();
			conn.close();
						

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//뒤로가기 전 pause
		System.out.println();
		System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
		scan.nextLine();
		
	}//procScoreInfoByStudent()

	


	private static String procScoreInfoTnameByStudent() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		String snum = procScoreBasicInfoByStudent();

		try {
			

			conn = util.open("211.63.89.64", "project" ,"java1234");
			String sql = "{ call procScoreInfoTnameByStudent(?,?)";
			
			stat = conn.prepareCall(sql);
			
			//실행 전 pause
			System.out.println();
			System.out.println();
			System.out.println("\t\t           엔터를 입력하시면 해당 교육생의 수강 과목과 기간이 출력됩니다.");
			scan.nextLine();
			
			

			stat.setString(1, snum);
			
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			System.out.println();
			System.out.println();
			System.out.println("\t        [개설과목명]           [시작일]        [종료일]      [교사명]");
			System.out.println("\t        ――――――――――――――――――――――――――――――――――――――――――――――――――――");
			
			while (rs.next()) {
				System.out.printf("\t\t%-15s\t  %-14s%-14s      %-10s\n"
						, rs.getString("subjectname")
						, rs.getString("startdate")
						, rs.getString("enddate")
						, rs.getString("teachername"));
			}

			
			stat.close();
			conn.close();
						

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return snum;
		
		
	}//procScoreInfoTnameByStudent()



	private static String procScoreBasicInfoByStudent() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		String snum = "";
		
		try {

			conn = util.open("211.63.89.64", "project" ,"java1234");
			String sql = "{ call procScoreBasicInfoByStudent(?,?)";
			
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t   교육생 개인별 성적 조회 ");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
			
			System.out.print("\t\t\t▷ 교육생 번호 입력 : "); //교육생 번호입력
			snum = scan.nextLine();
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.setString(1, snum);
			
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			System.out.println();
			System.out.println();
			System.out.println("[교육생 이름] [주민번호]     [시작일]      [종료일]     [강의실명]  [개설과정명]");
			System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			
			while (rs.next()) {
				System.out.printf("%-8s%16s%13s%13s%7s        %-50s\n"
						, rs.getString("studentname")
						, rs.getString("ssn")
						, rs.getString("startdate")
						, rs.getString("enddate")
						, rs.getString("classroomname") + "강의실"
						, rs.getString("coursename"));
			}
			
			stat.close();
			conn.close();
						

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return snum;
		
	}//procScoreBasicInfoByStudent()
	
	
	
	
	
	
	
	
	
	
	

}//AdminExam
