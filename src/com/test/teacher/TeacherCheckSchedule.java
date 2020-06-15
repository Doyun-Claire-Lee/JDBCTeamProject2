package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherCheckSchedule {

	// 강의 스케줄(시작)
	public void procCheckTeaching(TeacherUser tuser) {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		try {

			while (true) {

				conn = util.open("211.63.89.64", "project", "java1234");

				String sql = "{ call procCheckTeaching(?,?) }";
				stat = conn.prepareCall(sql);

				// 교사번호 get으로 가져 오기
				stat.setInt(1, tuser.getNum());

				stat.registerOutParameter(2, OracleTypes.CURSOR);

				stat.executeQuery();

				rs = (ResultSet) stat.getObject(2);

				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t\t강의 스케줄 조회");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("[과정번호]\t[과정명]\t\t\t\t\t\t  [강의실] [정원] [시작일]\t\t\t[종료일]\t\t\t[상태]");

				while (rs.next()) {

					System.out.printf("%4s\t%s %9s\t %3s   %s\t%s\t %s", rs.getString("coursenum"),
							rs.getString("coursename"), rs.getString("classroomnum"), rs.getString("stdNum"),
							rs.getString("startdate"), rs.getString("enddate"), rs.getString("status")

					);

					sql = "{ call procCheckTeaching2(?,?)}";
					stat2 = conn.prepareCall(sql);

					System.out.println();
				}

				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t과정 번호");
				System.out.println("\t\t\t0. 뒤로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t▷ 입력:");

				
					
				//010-3385-7130
				//1951320
				
				// 사용자 한테 입력 받는 값
				String cho = scan.nextLine();

				if (cho.equals("0")) {
					System.out.println();
					System.out.println("\t\t\t뒤로가기를 선택하셨습니다.");
					System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
					scan.nextLine();
					break;
				} else {
					
					System.out.println("IDK");
					// 사용자에게 과정 입력받고 과목 출력
					stat2.setString(1, cho);
					stat2.registerOutParameter(2, OracleTypes.CURSOR);
					stat2.executeQuery();
					rs2 = (ResultSet) stat2.getObject(2);
					
					System.out.println();
					System.out.println("[과목번호] [과목명]\t\t  [시작일]\t\t[종료일]\t\t\t  [교재명]");
					
					while (rs2.next()) {
						System.out.printf("%4s\t %s\t  %s\t %s \t  %s\n", rs2.getString("subjectnum"),
								rs2.getString("subjectname"), rs2.getString("sjStardDate"), rs2.getString("sjenddate"),
								rs2.getString("bookname"));
					}

					System.out.println();

					// 과목번호 입력시 해당 학생들 출력
					procCheckStdInfo();

				} // else

			} // while

			rs.close();
			rs2.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// 강의 스케줄(끝) //procCheckTeaching(TeacherUser tuser)

//==============================================================================================================	

	// 과목번호 입력시 해당 학생들 출력
	private static void procCheckStdInfo() {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		while (true) {

			try {
				conn = util.open("211.63.89.64", "project", "java1234");

				String sql = "{ call procCheckStdInfo(?,?) }";
				stat = conn.prepareCall(sql);

				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t과목 번호");
				System.out.println("\t\t\t0. 뒤로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t▷ 입력:");

				// 사용자 한테 입력 받는 값
				String cho = scan.nextLine();

				if (cho.equals("0")) {
					System.out.println();
					System.out.println("\t\t\t뒤로가기를 선택하셨습니다.");
					System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
					scan.nextLine();
					break;
				} else {

					stat.setString(1, cho);
					stat.registerOutParameter(2, OracleTypes.CURSOR);

					stat.executeQuery();

					rs = (ResultSet) stat.getObject(2);

					System.out.println();
					System.out.println("[학생명]\t [전화번호]\t[최초 등록일]\t\t[수료구분]");

					while (rs.next()) {

						System.out.printf("%5s\t %s\t%s\t%5s\n", rs.getString("stdName"), rs.getString("stdtel"),
								rs.getString("stdRegitdate"), rs.getString("status"));

					}

				} // else
				rs.close();
				stat.close();
				conn.close();

				System.out.println();
				System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
				scan.nextLine();

				break;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // while

	}// 과목번호 입력시 해당 학생들 출력(끝) //procCheckStdInfo()

}// class
