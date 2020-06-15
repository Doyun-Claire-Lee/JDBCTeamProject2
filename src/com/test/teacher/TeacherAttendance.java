package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;
/**
 *  
 * @author siyeon
 * 선생님이 출결 관리 및 출결 조회를 할 수 있는 클래스입니다.
 */
public class TeacherAttendance {
	
	Scanner	scan = new Scanner(System.in);
	
	/*
	public static void main(String[] args) {
		TeacherAttendance m = new TeacherAttendance();
		m.menu(3);
	}
	*/
	/**
	 *  출결 관리 및 출결 조회 전체 메뉴입니다.
	 * @param teacherNum 선생님 번호
	 */
	
	public void menu(TeacherUser teacherUser) {
		boolean loop = true;
		while(loop) {
		System.out.println("〓〓〓〓〓〓〓〓   M E N U 〓〓〓〓〓〓〓〓");
		System.out.println("          출결 관리 및 출결 조회");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("1. 출결 조회");
		System.out.println("2. 출결 현황(년,월,일) 조회");
		System.out.println("3. 특정(특정 과정, 특정 인원) 출결 현황 조회");
		System.out.println("4. 년,월 별로 출결 조회");
		System.out.println("0. 뒤로가기");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("▷입력:");
		
		int num = scan.nextInt();
		scan.skip("\r\n");
		
		switch(num) {
		case 1: 
			procteacherAttendance(teacherUser.getNum());
		break;
		case 2:
			procteacherAttendance1(teacherUser.getNum());
		break;
		case 3:
			procteacherAttendanceSelect(teacherUser.getNum());
		break;
		case 4:
			procteacherAttendanceStatus(teacherUser.getNum());
		break;
		case 0:
			loop = false;
			break;
		default:
			System.out.println("번호를 다시 입력해주세요.");
			}
		}

	} //menu()
	
	/**
	 * 선생님이 강의한 과정에 한해 과정 번호를 입력하면 교육생의 출결을 조회하는 기능입니다.
	 * @param teacherNum 선생님 번호
	 */
	public void procteacherAttendance(int teacherNum) {
		
		Connection conn = null;
		CallableStatement stat1 = null;
		Statement stat = null;
		DBUtil util = new DBUtil();
		String sql = null;
		ResultSet rs = null;

		try {
			//conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64","project","java1234");
			stat = conn.createStatement();
			sql = String.format("select * from vwteacherAttendance where teacherNum = %d",teacherNum);
			rs = stat.executeQuery(sql);
			//*** 선생님 교육생의 출결 조회 ***
		
			System.out.println("[openCourseNum]\t[allCourseNum]\t[subject name]\t[teacher name]\t[teacherNum]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("openCourseNum"));
				System.out.printf("%s\t",rs.getString("allCourseNum"));
				System.out.printf("%s\t",rs.getString("subject name"));
				System.out.printf("%s\t",rs.getString("teacher name"));
				System.out.printf("%s\t",rs.getString("teacherNum"));
				System.out.println();
			
				
			}
			System.out.println();
			stat.close();
			
			sql = "{call procteacherAttendance(?,?,?)}";
			stat1 = conn.prepareCall(sql);
			
			System.out.print("▷개설 과정 번호:");
			String num = scan.nextLine();

			stat1.setString(1,num);
			stat1.setInt(2, teacherNum);
			
			stat1.registerOutParameter(3,OracleTypes.CURSOR); 
			stat1.executeQuery();
			
			rs = (ResultSet)stat1.getObject(3); 
			int count = 0;
			System.out.println("[studentName]\t[teacherName]\t[coursePeriod]\t[courseStatus]\t[attendanceStatus]\t[enterTime]\t[outTime]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("studentName"));
				System.out.printf("%s\t",rs.getString("teacherName"));
				System.out.printf("%s\t",rs.getString("coursePeriod"));
				System.out.printf("%s\t",rs.getString("courseStatus"));
				System.out.printf("%s\t",rs.getString("attendanceStatus"));
				System.out.printf("%s\t",rs.getString("enterTime"));
				System.out.printf("%s\t",rs.getString("outTime"));
				System.out.println();
				
				count++;
				if(count%100==0) {
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("       -1을 누르면 종료");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("▷번호:");
					int k = scan.nextInt();
					scan.skip("\r\n");
					if(k==-1) {
						break;
					} else {
						pause();
					}
				}
				
			}
			conn.close();
			stat1.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("teacherAttendacne.procteacherAttendance()");
			e.printStackTrace();
		}
		
	} //procteacherAttendance()
	/**
	 * 계속할려면 엔터를 눌러야 하는 기능입니다.
	 */
	public void pause() {
		System.out.println("100개를 계속 출력하실려면 엔터를 누르세요...");
		scan.nextLine();
	}
	/**
	 * 선생님이 출결 현황을 기간별(년,월,일)로 조회하는 기능입니다.
	 * @param teacherNum 선생님 번호
	 */
	public void procteacherAttendance1(int teacherNum) {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		String sql = null;
		
		//*** 선생님 출결 현황을 기간별(년, 월, 일) 조회 ***
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("1. 년 조회");
		System.out.println("2. 월 조회");
		System.out.println("3. 일 조회");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("▷입력: ");
		String num = scan.nextLine();
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		try {
			//conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64","project","java1234");
			switch(num) {
			case "1": 
				sql = "{call procteacherAttendance1(?,?,?)}";
				stat = conn.prepareCall(sql);
				stat.registerOutParameter(1,OracleTypes.CURSOR); 
				System.out.print("▷년(yyyy):");
				String year = scan.nextLine();
				
				stat.setString(2,year);
				stat.setInt(3, teacherNum);
				stat.executeQuery(); // 다 넣고 업데이트임
				rs = (ResultSet)stat.getObject(1);
				System.out.println("[openCourseNum]\t[student name]\t[enterTime]\t\t\t[outTime]\t\t[attendance status]\t[course status]");
				while(rs.next()) {
					System.out.printf("%s\t\t",rs.getString("openCourseNum"));
					System.out.printf("%s\t\t",rs.getString("student name"));
					System.out.printf("%s\t\t",rs.getString("enterTime"));
					System.out.printf("%s\t\t",rs.getString("outTime"));
					System.out.printf("%s\t\t",rs.getString("attendance status"));
					System.out.printf("%s\t\t",rs.getString("course status"));
					System.out.println();
				}
				break;
			case "2":
				sql = "{call procteacherAttendance2(?,?,?)}";
				stat = conn.prepareCall(sql);
				stat.registerOutParameter(1,OracleTypes.CURSOR); 
				System.out.print("▷월(mm):");
				String month = scan.nextLine();
				
				stat.setString(2,month);
				stat.setInt(3, teacherNum);
				stat.executeQuery(); 
				rs = (ResultSet)stat.getObject(1);
				System.out.println("[courseHistoryNum]\t[student name]\t[enterTime]\t\t\t[outTime]\t\t[attendance status]\t[course status]");
				while(rs.next()) {
					System.out.printf("%s\t\t",rs.getString("openCourseNum"));
					System.out.printf("%s\t\t",rs.getString("student name"));
					System.out.printf("%s\t\t",rs.getString("enterTime"));
					System.out.printf("%s\t\t",rs.getString("outTime"));
					System.out.printf("%s\t\t",rs.getString("attendance status"));
					System.out.printf("%s\t\t",rs.getString("course status"));
					System.out.println();
				}
				break;
			case "3":
				sql = "{call procteacherAttendance3(?,?,?)}";
				stat = conn.prepareCall(sql);
				stat.registerOutParameter(1,OracleTypes.CURSOR); 
				System.out.print("▷일(dd):");
				String day = scan.nextLine();
		
				stat.setString(2,day);
				
				stat.setInt(3, teacherNum);
				stat.executeQuery();
				rs = (ResultSet)stat.getObject(1);
				System.out.println("[openCourseNum]\t[student name]\t[enterTime]\t\t\t[outTime]\t\t[attendance status]\t[course status]");
				while(rs.next()) {
					System.out.printf("%s\t\t",rs.getString("openCourseNum"));
					System.out.printf("%s\t\t",rs.getString("student name"));
					System.out.printf("%s\t\t",rs.getString("enterTime"));
					System.out.printf("%s\t\t",rs.getString("outTime"));
					System.out.printf("%s\t\t",rs.getString("attendance status"));
					System.out.printf("%s\t\t",rs.getString("course status"));
					System.out.println();
				}
				break;
				default: 
					System.out.println("번호를 다시 입력해주세요.");
			}
			System.out.println("완료");
			stat.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("teacherAttendance.procteacherAttendance1()");
			e.printStackTrace();
		}
		
		
	} //procteacherAttendance1()
	
	/**
	 * 선생님이 특정과정, 특정인원의 출결 현황을 조회하는 기능입니다.
	 * @param pteacherNum 선생님 번호
	 */
	public void procteacherAttendanceSelect(int pteacherNum) {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		Statement stat1 = null;
		DBUtil util = new DBUtil();
		
		String sql = null;
	
		//*** 선생님 특정과정,특정인원 출결현황 조회 ***
		try {
			//conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64","project","java1234");
			sql =String.format("select * from vwcourseHistory where teacherNum = %d",pteacherNum);
			stat1 = conn.createStatement();
			rs=stat1.executeQuery(sql);
			
			System.out.println("[student name]\t[openCourseNum]\t[student num]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("student name"));
				System.out.printf("%s\t",rs.getString("openCourseNum"));
				System.out.printf("%s\t",rs.getString("student num"));
				System.out.println();
			}
			
			System.out.print("▷개설 강좌 번호(특정 과정):");
			String num = scan.nextLine();
			System.out.print("▷학생 번호:");
			String name = scan.nextLine();
			
			sql = "{call procteacherAttendanceSelect(?,?,?,?)}";
			stat=conn.prepareCall(sql);
			stat.registerOutParameter(1,OracleTypes.CURSOR); 
			stat.setString(2, num);
			stat.setString(3, name);
			stat.setInt(4, pteacherNum);
			
			stat.executeQuery();
			rs = (ResultSet)stat.getObject(1);
			System.out.println("[course period]\t[course name]\t[enterTime]\t[outTime]\t[student name]");
			while(rs.next()) {
				System.out.printf("%s\t",rs.getString("course period"));
				System.out.printf("%s\t",rs.getString("course name"));
				System.out.printf("%s\t",rs.getString("enterTime"));
				System.out.printf("%s\t",rs.getString("outTime"));
				System.out.printf("%s\t",rs.getString("student name"));
				System.out.println();
			}
			rs.close();
			conn.close();
			stat.close();
			
		} catch (Exception e) {
			System.out.println("teacherAttendance.procteacherAttendanceSelect");
			e.printStackTrace();
		}
		
	} //procteacherAttendance1()
	
	/**
	 * 선생님이 년,월을 입력 한 후 근태 상황을 구분하여 출결조회를 할 수 있습니다. 
	 * @param teacherNum 선생님 번호
	 */
	public void procteacherAttendanceStatus(int teacherNum) {
		
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		String sql = null;
		//*** 선생님이 출결 조회 ***
		try {
			//conn = util.open("211.63.89.64","project","java1234");
			conn = util.open("211.63.89.64","project","java1234");
			sql = "{call procteacherAttendanceStatus(?,?,?,?)}";
			stat = conn.prepareCall(sql);
			stat.registerOutParameter(1,OracleTypes.CURSOR); 
			System.out.print("▷년(yyyy):");
			String year = scan.nextLine();
			System.out.print("▷월(mm):");
			String month = scan.nextLine();
		
			stat.setString(2, year);
			stat.setString(3, month);
			stat.setInt(4, teacherNum);
			
			stat.executeQuery();
			rs = (ResultSet)stat.getObject(1);
		
			System.out.println("[studentName]\t[enterTime]\t[outTime]\t[attendanceStatus]\t[openCourseNum]");
			while(rs.next()) {				
				System.out.printf("%s\t",rs.getString("studentName"));
				System.out.printf("%s\t",rs.getString("enterTime"));
				System.out.printf("%s\t",rs.getString("outTime"));
				System.out.printf("%s\t",rs.getString("attendanceStatus"));
				System.out.printf("%s\t",rs.getString("openCourseNum"));
				System.out.println();
			}
			
			
		} catch (Exception e) {
			System.out.println("teacherAttendance.procteacherAttendanceStatus()");
			e.printStackTrace();
		}

	}

} //procteacherAttendanceStatus()
