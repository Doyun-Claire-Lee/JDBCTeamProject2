package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleTypes;

/**
 * 
 * @author siyeon
 *관리자가 개설 과정 관리하는 클래스입니다.
 */

public class AdminOpencourse {

	Scanner	scan = new Scanner(System.in);

	/*
	public static void main(String[] args) {
		AdminOpencourse m = new AdminOpencourse();
		m.menu();
	}
	*/

	/**
	 *  개설 과정 관리 전체 메뉴입니다.
	 */
	public void menu() {
		
		Boolean loop = true;
		while (loop) {
			System.out.println("〓〓〓〓〓〓〓〓〓  M E N U 〓〓〓〓〓〓〓");
			System.out.println("                  개설 과정 관리");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 과정명, 과정기간, 강의실 정보 추가");
			System.out.println("2. 기초 정보 강의실명에서 수정");
			System.out.println("3. 개설 과정 정보 출력");
			System.out.println("4. 특정 개설 과정 선택 출력");
			System.out.println("5. 수료날짜 지정");
			System.out.println("6. 추가, 조희, 수정, 삭제 기능");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷번호:");
			int num = scan.nextInt();
			scan.skip("\r\n");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			switch (num) {
			case 1:
				procopenCourse();
				break;
			case 2:
				procBasicClassroom();
				break;
			case 3:
				vwopenCourseInfo();
				break;
			case 4:
				vwopenCourseSubject();
				break;
			case 5:
				vwopenCourseSelectEndDate();
				break;
			case 6:
				menu1();
				break;
			case 0:
				loop = false;
				break;
			default:
				System.out.println("번호를 다시 입력해주세요.");
			}
		}
	} // menu()

	/**
	 *  개설과정를 관리하는 소메뉴입니다.
	 */
	public void menu1() {

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
				procopenCourseInsert();
				break;
			case 2:
				vwopenCourse();
				break;
			case 3:
				procopenCourseUpdate();
				break;
			case 4:
				procopenCourseDelete();
				break;
			case 0:
				break;
			}
			break;
		}
	} // menu1()

	/**
	 *  기초 강의실, 과정명 정보와 개설 과정를 보고서 과정명, 과정기간, 강의실 정보를 추가를 합니다.
	 */
	
	//확인
	private void procopenCourse() {
		Connection conn = null;
		Statement stat1 = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		String sql = null;
		
		vwopenCourse();
		System.out.println();
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			stat1 = conn.createStatement();
			sql = "select * from vwclassRoom";
			rs = stat1.executeQuery(sql);
			System.out.println("*** 기초 강의실 정보 ***");
			System.out.println("[classRoomNum]\t[classRoomName]\t[capcity]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("classRoomNum"));
				System.out.printf("%s\t",rs.getString("classRoomName"));
				System.out.printf("%s\t",rs.getString("capacity"));
				System.out.println();
			}
			System.out.println();
			sql = "select * from vwallCourse1";
			rs = stat1.executeQuery(sql);
			System.out.println("*** 기초 과정 정보 ***");
			System.out.println("[allCourseNum]\t[coureName]\t[capacity]\t[coursePeriod]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("allCourseNum"));
				System.out.printf("%s\t",rs.getString("courseName"));		
				System.out.printf("%s\t",rs.getString("capacity"));
				System.out.printf("%s\t",rs.getString("coursePeriod"));
				System.out.println();
			}
			System.out.println();
			stat1.close();
			sql = "{ call procopenCourse(?,?,?,?) }";
			stat = conn.prepareCall(sql);
			// num,startDate,endDate,classRoomNum,allCourseNum,status
			System.out.println("[개설 과정 정보 시작날짜]");

			System.out.print("▷시작날짜 년(yyyy):");
			String startyear = scan.nextLine();
			System.out.print("▷시작날짜 월(mm):");
			String startmonth = scan.nextLine();
			System.out.print("▷시작날짜 일(dd):");
			String startday = scan.nextLine();

			System.out.println("[개설 과정 정보 종료날짜]");
			System.out.print("▷종료날짜 년(yyyy):");
			String endyear = scan.nextLine();
			System.out.print("▷종료날짜 월(mm):");
			String endmonth = scan.nextLine();
			System.out.print("▷종료날짜 일(dd):");
			String endday = scan.nextLine();

			System.out.print("▷강의실 번호:");
			String classRoomNum = scan.nextLine();

			System.out.print("▷과정 번호:");
			String allCourseNum = scan.nextLine();
			stat.setString(1, startyear + startmonth + startday);
			stat.setString(2, endyear + endmonth + endday);
			stat.setString(3, classRoomNum);
			stat.setString(4, allCourseNum);

			stat.executeUpdate();

			stat.close();
			conn.close();

			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminOpencourse.procopenCourse()");
			e.printStackTrace();
		}

	} // procOpenCourse()

	/**
	 *  강의실 정보를 기초 정보 강의실명에서 선택적으로 수정합니다.
	 */
	
	private void procBasicClassroom() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat1 = null;
	
		// *** 강의실 정보는 기초 정보 강의실명에서 선택적으로 추가 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement();
			String sql = "select * from vwopenCourseClassRoom";
			rs = stat.executeQuery(sql);
			System.out.println("[개설 강좌 번호]\t[시작 날짜]\t[종료 날짜]\t[강의실 번호]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("opening course number"));
				System.out.printf("%s\t", rs.getString("startDate"));
				System.out.printf("%s\t", rs.getString("endDate"));
				System.out.printf("%s\t", rs.getString("classRoomNum"));
				System.out.println();
			}
			sql = "{ call procBasicClassroom(?,?) }";

			stat1 = conn.prepareCall(sql);
			System.out.print("▷수정할 개설 강좌번호:");
			String num = scan.nextLine();
			stat1.setString(1, num);
			System.out.print("▷강의실 번호:");
			String classroom = scan.nextLine();
			stat1.setString(2, classroom);
			stat1.executeUpdate();

			conn.close();
			stat.close();
			stat1.close();
			rs.close();
			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminOpencourse.procBasicClassroom()");
			e.printStackTrace();
		}

	} // procBasicClassroom()

	/**
	 * 개설 과정 정보 출력시 개설 과정명, 개설 과정기간, 강의실명, 개설 과목 등록 여부, 교육생 등록 인원을 출력합니다.
	 */
	public void vwopenCourseInfo() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
	
		System.out.println(
				"[openCourseNum]\t[openingCourseName]\t[openingCoursePeriod]\t[classroomName]\t[registerOpeningCourse]\t[traineeRegistrationPersonnel]");
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement(); // 쿼리를 날릴 수 있는 개체
			String sql = "select * from vwopenCourseInfo";

			rs = stat.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("openCourseNum"));
				System.out.printf("%s\t", rs.getString("opening course name"));
				System.out.printf("%s\t", rs.getString("opening course period"));
				System.out.printf("%s\t", rs.getString("classroom name"));
				System.out.printf("%s\t", rs.getString("register opening course"));
				System.out.printf("%s\t", rs.getString("trainee registration personnel"));
				System.out.println();

				count++;
				if (count % 100 == 0) {
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("           0. 뒤로가기");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("번호:");
					int k = scan.nextInt();
					scan.skip("\r\n");
					if (k == 0) {
						break;
					} else {
						pause();
					}
				}
			}
			conn.close();
			stat.close();
			rs.close();
			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminOpenCourse.vwopenCourseInfo()");
			e.printStackTrace();
		}

	} // vwopenCourseInfo()
	
	/**
	 * 계속 할려면 엔터를 눌러야 합니다.
	 */

	public void pause() {
		System.out.println("100개를 계속 출력하실려면 엔터를 누르세요...");
		scan.nextLine();
	}

	/**
	 *  기초 정보 과정명을 보고서 과정 번호를 입력하면 개설 과목 정보(과목명, 과목기간 및 등록된 교육생 정보)를 보여줍니다.
	 */
	public void vwopenCourseSubject() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		String sql = null;
		// 특정 개설 과정 선택시 개설 과정에 등록된 개설 과목 정보(과목명, 과목기간 및 등록된 교육생 정보)
		
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement(); // 쿼리를 날릴 수 있는 개체
			sql = "select * from vwopenCourseName";
			rs = stat.executeQuery(sql);
			System.out.println("[openCourseNum]\t[courseName]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("openCourseNum"));
				System.out.printf("%s\t",rs.getString("courseName"));
				System.out.println();
			}
			System.out.println();
			System.out.print("▷과정 번호:");
			int num = scan.nextInt();
			scan.skip("\r\n");
			sql = String.format("select * from vwopenCourseSubject where num = %d", num);

			rs = stat.executeQuery(sql);
			System.out.println(
					"[subjectName]\t[subjectDuration]\t[textBookName]\t[teacherName]\t[studentName]\t[ssn]\t[tel]\t[registrationDate]\t[completionStatus]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("subjectname"));
				System.out.printf("%s\t", rs.getString("Subject duration"));
				System.out.printf("%s\t", rs.getString("textbook name"));
				System.out.printf("%s\t", rs.getString("teacher name"));
				System.out.printf("%s\t", rs.getString("student name"));
				System.out.printf("%s\t", rs.getString("ssn"));
				System.out.printf("%s\t", rs.getString("tel"));
				System.out.printf("%s\t", rs.getString("registration date"));
				System.out.printf("%s\t", rs.getString("completion status"));
				System.out.println();
			}
			conn.close();
			rs.close();
			stat.close();

		} catch (Exception e) {
			System.out.println("AdminOpenCourse.vwopenCourseSubject()");
			e.printStackTrace();
		}

	} // vwopenCourseSubject()

	/**
	 * 과정 수료 상태를 보여주고서 등록된 교육생 전체에 대해서 수료날짜를 지정합니다.
	 */
	public void vwopenCourseSelectEndDate() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		Statement stat1 = null;
		DBUtil util = new DBUtil();
		String sql = null;
		// *** 특정 개설 과정이 수료한 경우 등록된 교육생 전체에 대해서 수료날짜를 지정 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			sql = "{call  procstudentEndDate(?,?)}";
			stat = conn.prepareCall(sql);
			stat1 = conn.createStatement();
			sql = "select * from vwopenCourseSelectEndDate";
			rs = stat1.executeQuery(sql);
			System.out.println("[courseHistoryNum]\t[openCourseNum]\t[status]\t[student name]\t[endDate]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("courseHistoryNum"));
				System.out.printf("%s\t", rs.getString("openCourseNum"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.printf("%s\t", rs.getString("student name"));
				System.out.printf("%s\t", rs.getString("endDate"));
				System.out.println();

			}

			System.out.print("▷특정 개설 과정 번호(courseHistoryNum):");
			String openCourseNum = scan.nextLine();
			System.out.println("[과정 수료 날짜]");
			System.out.print("▷과정 수료 년(yyyy):");
			String endyear = scan.nextLine();
			System.out.print("▷과정 수료 월(mm):");
			String endmonth = scan.nextLine();
			System.out.print("▷과정 수료 일(dd):");
			String endday = scan.nextLine();

			stat.setString(1, endyear + endmonth + endday);
			stat.setString(2, openCourseNum);

			stat.executeUpdate();

			System.out.println("완료");

			sql = "select * from vwopenCourseSelectEndDate";
			rs = stat1.executeQuery(sql);
			System.out.println("[courseHistoryNum]\t[openCourseNum]\t[status]\t[student name]\t[endDate]");
			while (rs.next()) {
				System.out.printf("%s\t", rs.getString("courseHistoryNum"));
				System.out.printf("%s\t", rs.getString("openCourseNum"));
				System.out.printf("%s\t", rs.getString("status"));
				System.out.printf("%s\t", rs.getString("student name"));
				System.out.printf("%s\t", rs.getString("endDate"));
				System.out.println();

			}
			stat.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("AdminOpenCourse.vwopenCourseSelectEndDate()");
			e.printStackTrace();
		}
	} // vwopenCourseSelectEndDate()

	/**
	 * 개설 과정 정보, 기초 강의실 정보, 기초 과정명 정보, 기초 선생님 정보를 출력 한 후 개설 과정 정보에 대한 추가 기능입니다.
	 */
	public void procopenCourseInsert() {

		Connection conn = null;
		CallableStatement stat = null;
		Statement stat1 = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		// 개설 과정 정보에 대한 추가 기능
	
		String sql = null;
		vwopenCourse();

		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			
			sql = "select * from vwclassRoom";
			stat1 = conn.createStatement();
			rs = stat1.executeQuery(sql);
			System.out.println("*** 기초 강의실 정보 ***");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("classRoomNum"));
				System.out.printf("%s\t",rs.getString("classRoomName"));
				System.out.println();
			}
			System.out.println();
			
			System.out.println("*** 기초 과정명 정보 ***");
			sql = "select * from vwallCourse";
			stat1 = conn.createStatement();
			rs = stat1.executeQuery(sql);
			System.out.println("[allCourseNum]\t[courseName]\t[subjectPeriod]\t[subjectName]\t[capacity]\t[coursePeriod]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("allCourseNum"));
				System.out.printf("%s\t",rs.getString("courseName"));
				System.out.printf("%s\t",rs.getString("subjectperiod"));
				System.out.printf("%s\t",rs.getString("subjectName"));
				System.out.printf("%s\t",rs.getString("capacity"));
				System.out.printf("%s\t",rs.getString("coursePeriod"));
				System.out.println();
			}
			System.out.println();
			
			System.out.println("*** 기초 선생님 정보 ***");
			sql = "select * from vwteacher";
			stat1 = conn.createStatement();
			rs = stat1.executeQuery(sql);
			System.out.println("[teacherNum]\t[availablesubjectName]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("teacherNum"));
				System.out.printf("%s\t",rs.getString("availablesubjectName"));
				System.out.println();
			}
			System.out.println();
			
			sql = "{ call procopenCourseInsert(?,?,?,?,?) }";
			stat = conn.prepareCall(sql);

			System.out.println("[개설 과정 정보 시작날짜]");

			System.out.print("▷시작날짜 년(yyyy):");
			String startyear = scan.nextLine();
			System.out.print("▷시작날짜 월(mm):");
			String startmonth = scan.nextLine();
			System.out.print("▷시작날짜 일(dd):");
			String startday = scan.nextLine();

			System.out.println("[개설 과정 정보 종료날짜]");
			System.out.print("▷종료날짜 년(yyyy):");
			String endyear = scan.nextLine();
			System.out.print("▷종료날짜 월(mm):");
			String endmonth = scan.nextLine();
			System.out.print("▷종료날짜 일(dd):");
			String endday = scan.nextLine();

			System.out.print("▷개설 과정 반 번호:");
			String classroom = scan.nextLine();
			System.out.print("▷선생님 번호:");
			String teacherNum = scan.nextLine();
			System.out.print("▷과정 정보: ");
			String allCourseNum = scan.nextLine();

			stat.setString(1, startyear + startmonth + startday);
			stat.setString(2, endyear + endmonth + endday);
			stat.setString(3, classroom);
			stat.setString(4, teacherNum);
			stat.setString(5, allCourseNum);

			stat.executeUpdate();

			stat.close();
			conn.close();

			System.out.println("완료");
		} catch (Exception e) {
			System.out.println("AdminOpenCourse.procopenCourseInsert()");
			e.printStackTrace();
		}

	} // procopenCourseInsert()

	/**
	 *  개설 과정 목록을 출력한 후 선택적으로 개설 과정 정보에 대한 수정기능입니다.
	 */
	public void procopenCourseUpdate() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		//Statement stat1 = null;

		String sql = null;
		String ostartDate = null;
		String oendDate = null;
		String oclassRoomNum = null;
		String oteacherNum = null;
		String oallCourseNum = null;
		vwopenCourse();
		// 개설 과정 정보에 대한 수정 기능
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");

			sql = "{ call procopenCourseSelect(?,?) }";
			stat = conn.prepareCall(sql);
			System.out.print("▷시행 과정 번호:");
			String num = scan.nextLine();
			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);
			String a = null;
			String b = null;
			if (rs.next()) {
				ostartDate = rs.getString("startDate");
				oendDate = rs.getString("endDate");
				oclassRoomNum = rs.getString("classRoomNum");
				oteacherNum = rs.getString("teacherNum");
				oallCourseNum = rs.getString("allCourseNum");
			}

			rs.close();

			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 과정 시작 날짜 수정");
			System.out.println("2. 과정 종료 날짜 수정");
			System.out.println("3. 과정 강의실 번호 수정");
			System.out.println("4. 선생님 번호 수정");
			System.out.println("5. 과정 번호 수정");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷입력:");
			String sel = scan.nextLine();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			switch (sel) {
			case "1":
				System.out.print("▷과정 시작 년(yyyy):");
				String start_year = scan.nextLine();
				System.out.print("▷과정 시작 월(mm):");
				String start_month = scan.nextLine();
				System.out.print("▷과정 시작 일(dd):");
				String start_day = scan.nextLine();
				//// String sql = "update tblAddress set age = age + 1";
				// String startDate = start_year + start_month + start_day;

				sql = "{call procopenCourseUpdate(?,?,?,?,?,?)}";
				stat = conn.prepareCall(sql);
				stat.setString(1, num);
				stat.setString(2, start_year + start_month + start_day);
				a = oendDate.substring(0, 10); // yyyy-mm-dd hh:mm:ss
				oendDate = a.replace("-", "");
				stat.setString(3, oendDate);
				stat.setString(4, oclassRoomNum);
				stat.setString(5, oteacherNum);
				stat.setString(6, oallCourseNum);
				stat.executeUpdate();

				stat.close();
				System.out.println("완료");
				break;
			case "2":
				System.out.print("▷과정 종료 년(yyyy):");
				String end_year = scan.nextLine();
				System.out.print("▷과정 종료 월(mm):");
				String end_month = scan.nextLine();
				System.out.print("▷과정 종료 일(dd):");
				String end_day = scan.nextLine();

				sql = "{call procopenCourseUpdate(?,?,?,?,?,?)}";
				stat = conn.prepareCall(sql);
				stat.setString(1, num);
				b = ostartDate.substring(0, 10);
				ostartDate = b.replace("-", "");
				stat.setString(2, ostartDate);
				stat.setString(3, end_year + end_month + end_day);
				stat.setString(4, oclassRoomNum);
				stat.setString(5, oteacherNum);
				stat.setString(6, oallCourseNum);
				stat.executeUpdate();

				stat.close();
				System.out.println("완료");
				break;
			case "3":
				System.out.print("▷과정 강의실 번호:");
				String classRoomNum = scan.nextLine();
				sql = "{call procopenCourseUpdate(?,?,?,?,?,?)}";
				stat = conn.prepareCall(sql);
				a = oendDate.substring(0, 10);
				oendDate = a.replace("-", "");
				b = ostartDate.substring(0, 10);
				ostartDate = b.replace("-", "");
				stat.setString(1, num);
				stat.setString(2, ostartDate);
				stat.setString(3, oendDate);
				stat.setString(4, classRoomNum);
				stat.setString(5, oteacherNum);
				stat.setString(6, oallCourseNum);
				stat.executeUpdate();

				stat.close();
				System.out.println("완료");
				break;
			case "4":
				System.out.print("▷선생님 번호:");
				String teacherNum = scan.nextLine();
				sql = "{call procopenCourseUpdate(?,?,?,?,?,?)}";
				stat = conn.prepareCall(sql);
				a = oendDate.substring(0, 10);
				oendDate = a.replace("-", "");
				b = ostartDate.substring(0, 10);
				ostartDate = b.replace("-", "");
				stat.setString(1, num);
				stat.setString(2, ostartDate);
				stat.setString(3, oendDate);
				stat.setString(4, oclassRoomNum);
				stat.setString(5, teacherNum);
				stat.setString(6, oallCourseNum);
				stat.executeUpdate();

				stat.close();
				System.out.println("완료");
				break;
			case "5":
				System.out.print("▷과정 번호:");
				String allCourseNum = scan.nextLine();
				sql = "{call procopenCourseUpdate(?,?,?,?,?,?)}";
				stat = conn.prepareCall(sql);
				a = oendDate.substring(0, 10);
				oendDate = a.replace("-", "");
				b = ostartDate.substring(0, 10);
				ostartDate = b.replace("-", "");
				stat.setString(1, num);
				stat.setString(2, ostartDate);
				stat.setString(3, oendDate);
				stat.setString(4, oclassRoomNum);
				stat.setString(5, oteacherNum);
				stat.setString(6, allCourseNum);
				stat.executeUpdate();

				stat.close();
				System.out.println("완료");
				break;

			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("AdminOpenCourse.procopenCourseUpdate()");
			e.printStackTrace();
		}

	} // procopenCourseUpdate()
	/**
	 * 개설 과정 목록을 보여준 후 삭제하는 기능입니다.
	 */
	public void procopenCourseDelete() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();


		String num = null;
		vwopenCourse();
		// *** 개설 과정 정보에 대한 삭제 기능 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			// conn = util.open("211.63.89.64","project","java1234");
			String sql = "{ call procopenCourseDelete(?) }";
			stat = conn.prepareCall(sql);
			System.out.print("▷삭제할 시행 과정 번호:");
			num = scan.nextLine();
			stat.setString(1, num);
			stat.executeUpdate();

			stat.close();
			conn.close();
			System.out.println("완료");

		} catch (Exception e) {
			System.out.println("AdminOpenCourse.procopenCourseDelete()");
			e.printStackTrace();
		}

	} // procopenCourseDelete()

	/**
	 *  개설 과정 정보에 대한 출력기능입니다.
	 */
	public void vwopenCourse() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		// *** 개설 과정 정보에 대한 출력 기능 ***
		try {
			// conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement(); // 쿼리를 날릴 수 있는 개체
			String sql = "select * from vwopenCourse";
			rs = stat.executeQuery(sql);
			System.out.println(
					"[openCourseNum]\t[startDate]\t\t[endDate]\t\t[classRoomNum]\t[teacherNum]\t[allCourseNum]");
			while (rs.next()) {
				
				System.out.printf("%s\t\t", rs.getString("num"));
				System.out.printf("%s\t", rs.getString("startDate"));
				System.out.printf("%s\t", rs.getString("endDate"));
				System.out.printf("%s\t\t", rs.getString("classRoomNum"));
				System.out.printf("%s\t\t", rs.getString("teacherNum"));
				System.out.printf("%s\t", rs.getString("allCourseNum"));
				System.out.println();
			}
			stat.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("AdminOpenCourse.vwopenCourse()");
			e.printStackTrace();
		}
	} // vwopenCourse()

} // AdminOpenCourse
