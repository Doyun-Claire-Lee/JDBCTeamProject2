package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/**
 * 
 * @author siyeon 관리자가 교육생 관리하는 클래스입니다.
 */
public class AdminStudent {

	Scanner scan = new Scanner(System.in);

	/*
	 * public static void main(String[] args) { AdminStudent m = new AdminStudent();
	 * m.menu(); }
	 */
	/**
	 * 교육생 관리 전체 메뉴입니다.
	 */
	public void menu() {
		boolean loop = true;
		while (loop) {
			System.out.println("〓〓〓〓〓〓〓〓  M E N U 〓〓〓〓〓〓〓〓");
			System.out.println("                    교육생 관리");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 취업완료 수료생 관리");
			System.out.println("2. 상담 관리");
			System.out.println("3. 성적 우수자");
			System.out.println("4. 보강 수업");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			int sel = scan.nextInt();
			scan.skip("\r\n");

			switch (sel) {
			case 1:
				menu1();
				break;
			
			case 0:
				loop = false;
				break;
			}

		}

	} // menu()

	/**
	 * 취업완료 수료생 관리 전체 메뉴입니다.
	 */

	public void menu1() {
		boolean loop = true;
		while (loop) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 고용보험 여부 조회 & 결과 수정");
			System.out.println("2. 취업완료 수료생 관리(추가,조회,수정,삭제)");
			System.out.println("3. 연봉별 검색");
			System.out.println("4. 회사명 검색");
		
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			int sel = scan.nextInt();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			scan.skip("\r\n");
			switch (sel) {
			case 1:
				prochiredGraduatesUpdate();
				break;
			case 2:
				menu2();
				break;
			case 3:
				prochiredgradesSalarySelect();
				break;
			case 4:
				prochiredGradesSelectName();
				break;
			case 0:
				loop = false;
				break;
			default:
				System.out.println("번호를 다시 입력해주세요.");
			}
		}

	} //menu1()

	/**
	 *  취업완료 수료생 관리 소메뉴(추가,조회,수정,삭제)입니다.
	 */
	
	public void menu2() {

		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 추가");
			System.out.println("2. 조회");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			int sel = scan.nextInt();
			scan.skip("\r\n");
			switch (sel) {
			case 1:
				prochiredGradesManInsert();
				break;
			case 2:
				vwhiredGradesManSelect();
				break;
			case 3:
				prochiredGraduatesUpdate1();
				break;
			case 4:
				prochiredGradesManDelete();
				break;
			case 0:
				break;
			}
			break;
		}
	} // menu2()
	
	/**
	 * 관리자는 수료생의 고용보험 여부를 조회 후 그 결과를 입력합니다.
	 */
	
	public void prochiredGraduatesUpdate() {
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		Statement stat1 = null;
		DBUtil util = new DBUtil();

		String sql = null;
		// *** 관리자는 수료생의 고용보험 여부를 조회 후 그 결과를 입력 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			stat1 = conn.createStatement();
			sql = "select * from vwhiredGraduatesSelect";
			rs = stat1.executeQuery(sql);
			System.out.println(
					"[hiredgraduates num]\t[courseHistoryNum]\t[company]\t[salary]\t[status]\t[employment insurance]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("num"));
				System.out.printf("%s\t", rs.getString("courseHistoryNum"));
				System.out.printf("%s\t", rs.getString("company"));
				System.out.printf("%s\t", rs.getString("salary"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.printf("%s\t", rs.getString("employment insurance"));
				System.out.println();
			}
			stat1.close();
			rs.close();
			System.out.print("▷수정할 취직 번호(hiredgraduates num):");
			String num = scan.nextLine();
			sql = "{call prochiredGraduatesUpdate(?)}";
			stat = conn.prepareCall(sql);
			stat.setString(1, num);
			stat.executeUpdate();

			stat.close();
			conn.close();
			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredGraduatesUpdate()");
			e.printStackTrace();
		}

	}

	/**
	 * 취업완료 수료생 관리 중 수정 기능입니다.
	 */
	public void prochiredGraduatesUpdate1() {

		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;

		DBUtil util = new DBUtil();

		String sql = null;

		String pcourseHistoryNum = null;
		String pcompany = null;
		String psalary = null;
		String pstatus = null;
		// *** 수정 기능 ***
		vwhiredGradesManSelect();
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			// conn = util.open("localhost","project","java1234");
			sql = "{call prochiredGraduatesUpdateSelect(?,?)}";
			stat = conn.prepareCall(sql);
			System.out.print("▷수정할 번호(hiredGraduatesNum):");
			String num = scan.nextLine();
			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			Statement stat1 = null;
			rs = (ResultSet) stat.getObject(2);

			// 원본 데이터
			if (rs.next()) {
				pcourseHistoryNum = rs.getString("courseHistoryNum");
				pcompany = rs.getString("company");
				psalary = rs.getString("salary");
				pstatus = rs.getString("status");
			}
			rs.close();

			sql = "{call prochiredGraduatesUpdate1(?,?,?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 수강내역 번호");
			System.out.println("2. 회사명");
			System.out.println("3. 연봉");
			System.out.println("4. 재직 상태");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			String a = scan.nextLine();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			switch (a) {

			case "1":
				stat1 = conn.createStatement();
				System.out.println("*** 기초 과정명 ***");
				sql = "select * from vwallCourse";
				rs = stat1.executeQuery(sql);
				while (rs.next()) {
					System.out.printf("%s\t", rs.getString("allCourseNum"));
					System.out.printf("%s\t", rs.getString("courseName"));
					System.out.println();
				}
				System.out.println();
				System.out.print("▷수강내역 번호(courseHistoryNum):");
				String courseHistoryNum = scan.nextLine();
				stat.setString(1, num);
				stat.setString(2, courseHistoryNum);
				stat.setString(3, pcompany);
				stat.setString(4, psalary);
				stat.setString(5, pstatus);

				break;
			case "2":
				System.out.print("▷회사이름:");
				String company = scan.nextLine();
				stat.setString(1, num);
				stat.setString(2, pcourseHistoryNum);
				stat.setString(3, company);
				stat.setString(4, psalary);
				stat.setString(5, pstatus);

				break;
			case "3":
				System.out.print("▷연봉:");
				String salary = scan.nextLine();
				stat.setString(1, num);
				stat.setString(2, pcourseHistoryNum);
				stat.setString(3, pcompany);
				stat.setString(4, salary);
				stat.setString(5, pstatus);
				break;
			case "4":
				System.out.print("▷취업 상태:");
				String status = scan.nextLine();
				stat.setString(1, num);
				stat.setString(2, pcourseHistoryNum);
				stat.setString(3, pcompany);
				stat.setString(4, psalary);
				stat.setString(5, status);
				break;
			}
			stat.executeUpdate();

			vwhiredGradesManSelect();
			stat.close();
			conn.close();

			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredGraduatesUpdate1()");
			e.printStackTrace();
		}

	}
	/**
	 *  취업 완료 수료생 출력하는 기능입니다.
	 */

	public void prochiredgradesSalarySelect() {
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;

		// *** 관리자 취업 완료 수료생 리스트 조회 시 연봉별 검색 ***
		try {

			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			sql = "{call prochiredgradesSalarySelect(?,?,?)}";

			stat = conn.prepareCall(sql);
			System.out.print("▷시작 연봉:");
			String start = scan.nextLine();
			System.out.print("▷끝 연봉:");
			String end = scan.nextLine();

			stat.setString(1, start);
			stat.setString(2, end);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			stat.executeQuery();
			rs = (ResultSet) stat.getObject(3);

			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("coursehistorynum"));
				System.out.printf("%s\t", rs.getString("company"));
				System.out.printf("%s\t", rs.getString("salary"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.println();
			}
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredgradesSalarySelect()");
			e.printStackTrace();
		}

	}
	/**
	 *  취업완료 수료생을 회사 이름으로 검색하는 기능입니다.
	 */
	public void prochiredGradesSelectName() {
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;

		// *** 관리자 회사명으로 조회 ***
		try {

			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			sql = "{call prochiredGradesSelectName(?,?)}";
			stat = conn.prepareCall(sql);
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			System.out.print("▷회사 이름:");
			String company = scan.nextLine();
			stat.setString(1, company);
			stat.executeQuery();
			rs = (ResultSet) stat.getObject(2);
			System.out.println("[hiredGraduatesNum]\t[courseHistoryNum]\t[company]\t[salary]\t[status]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("num"));
				System.out.printf("%s\t", rs.getString("courseHistoryNum"));
				System.out.printf("%s\t", rs.getString("company"));
				System.out.printf("%s\t", rs.getString("salary"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.println();
			}
			conn.close();
			rs.close();
			stat.close();

		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredGradesSelectName()");
			e.printStackTrace();
		}

	}

	/**
	 *  취업 완료 수료생을 삭제하는 기능입니다.
	 */
	public void prochiredGradesManDelete() {
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;

		vwhiredGradesManSelect();
		// *** 관리자가 취업 완료 수료생 관리 삭제 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			sql = "{call prochiredGradesManDelete(?)}";
			System.out.print("▷삭제할 번호(hiredGraduatesNum): ");
			String num = scan.nextLine();

			stat = conn.prepareCall(sql);
			stat.setString(1, num);
			stat.executeUpdate();

			System.out.println("완료");

			conn.close();
			stat.close();

		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredGradesManDelete()");
			e.printStackTrace();
		}

	}

	/**
	 *  취업 완료 수료생을 추가하는 기능입니다.
	 */
	public void prochiredGradesManInsert() {
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;
		Statement stat1 = null;

		// *** 관리자가 취업 완료 수료생 관리 추가 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			sql = "{call prochiredGradesManInsert(?,?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("*** 기초 과정명 ***");
			stat1 = conn.createStatement();
			sql = "select * from vwallCourse";
			rs = stat1.executeQuery(sql);
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("allCourseNum"));
				System.out.printf("%s\t", rs.getString("courseName"));
				System.out.println();
			}
			System.out.println();

			System.out.print("▷강좌 번호(pcourseHistoryNum):");
			String num = scan.nextLine();
			System.out.print("▷회사이름:");
			String company = scan.nextLine();
			System.out.print("▷연봉:");
			String salary = scan.nextLine();
			System.out.print("▷고용 상태(재직중,퇴사):");
			String status = scan.nextLine();

			stat.setString(1, num);
			stat.setString(2, company);
			stat.setString(3, salary);
			stat.setString(4, status);

			System.out.println("완료");

			stat.executeUpdate();

			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("AdminStudent.prochiredGradesManInsert()");
			e.printStackTrace();
		}

	}

	/**
	 *  취업 완료 수료생을 출력하는 기능입니다.
	 */
	public void vwhiredGradesManSelect() {
		Connection conn = null;
		ResultSet rs = null;
		Statement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;

		// *** 관리자가 취업 완료 수료생 관리 출력 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("localhost", "project", "java1234");
			sql = "select * from vwhiredGradesManSelect";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			int count = 0;
			System.out.println("[hiredgraduates num]\t[courseHistoryNum]\t[company]\t[salary]\t[status]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("num"));
				System.out.printf("%s\t", rs.getString("courseHistoryNum"));
				System.out.printf("%s\t", rs.getString("company"));
				System.out.printf("%s\t", rs.getString("salary"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.println();

				count++;
				if (count % 100 == 0) {
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("       0. 뒤로가기");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("▷번호:");
					int k = scan.nextInt();
					scan.skip("\r\n");
					if (k == 0) {
						break;
					} else {
						pause();
					}
				}
			}
			rs.close();
			conn.close();
			stat.close();

		} catch (Exception e) {
			System.out.println("AdminStudent.vwhiredGradesManSelect()");
			e.printStackTrace();
		}

	}

	/**
	 * 엔터를 눌러야지 계속 할 수 있는 기능입니다.
	 */
	public void pause() {
		System.out.println("100개를 계속 출력하실려면 엔터를 누르세요...");
		scan.nextLine();
	}

}
