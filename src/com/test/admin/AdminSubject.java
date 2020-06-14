package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class AdminSubject {
	
	DBUtil util = new DBUtil();
	CallableStatement stat = null;
	

	public void menu() {
		
		while(true) {
			
			Connection conn = null;
			Scanner scan = new Scanner(System.in);
			// Database Connection
			conn = util.open("localhost", "project", "java1234");
			
			System.out.println("〓〓〓〓〓〓〓〓〓 개설 과정별 과목 관리 〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 조회");
			System.out.println("2. 과목별 기간 등록");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 교재 배부");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력: \n");
			
			String sel = scan.nextLine();
			
			// 조회
			if(sel.equals("1")) {
				 PrintSjInfoPeriodBySubject(conn, scan);
			}
			// 등록
			// enroll new Subject
			else if (sel.equals("2")) {
				enrollPeriodBySubject(conn, scan);
			}
			
			// 수정
			// modify subject info
			else if (sel.equals("3")) {
				modifyPeriodBySubject(conn,scan);
			}
			// 삭제
			// delete subject info
			else if (sel.equals("4")) {
				deletePeriodBySubject(conn,scan);
			}
			else if (sel.equals("5")) {
				// 교재 배부
			}
			// exit
			else if(sel.equals("0")) {
				break;
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
			}
		
		}
	}

	private void deletePeriodBySubject(Connection conn, Scanner scan) {
		// print opencourse info
		vwOpencourse(conn);
		
		// input allcoursenum
		System.out.println("과정 번호:");
		String openCourseNum = scan.nextLine();
		
		try {
			
			// declare variable
			String sql = "{ call procPrintSjInfoPeriodBySubject(?,?,?)}";
			CallableStatement stat = conn.prepareCall(sql);
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			
			
			// set	
			stat.setString(1, openCourseNum); 
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			stat.executeUpdate();
			
			// get result set
			rs = (ResultSet) stat.getObject(2);
			rs2 = (ResultSet) stat.getObject(3);
			
			// print rs
			System.out.println("[과정명]\t[시작년월일]\t[종료년웡릴]\t[강의실명]\t[교사명]");
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n",
						rs.getString("ocName"),
						rs.getString("startdate"),
						rs.getString("enddate"),
						rs.getString("classroomnum"),						
						rs.getString("teachername")				
						);
			}
			
			// print rs2
			System.out.println("[구성 과목 리스트]");
			System.out.println("[과목명]\t[시작년월일]\t[종료년월일]\t[교재명]\t");
			while(rs2.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n",
						rs2.getString("subjectnum"),
						rs2.getString("subjectname"),
						rs2.getString("startdate"),
						rs2.getString("enddate"),
						rs2.getString("bookname")				
						);
			}
			
			// input subject number
			System.out.println("과목 번호:");
			String subjectnum = scan.nextLine();
			
			rs.close();
			rs2.close();
			stat.close();
			
			Statement stat2 = conn.createStatement();
			sql = String.format("delete from tblperiodBySubject where num = %s", subjectnum);
		
			stat2.executeUpdate(sql);
			
			// fin message
			System.out.println("삭제가 완료되었습니다.");
			stat2.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void modifyPeriodBySubject(Connection conn, Scanner scan) {
		
		// print opencourse info
				vwOpencourse(conn);
				
				// input allcoursenum
				System.out.println("과정 번호:");
				String openCourseNum = scan.nextLine();
				
				try {
					
					// declare variable
					String sql = "{ call procPrintSjInfoPeriodBySubject(?,?,?)}";
					CallableStatement stat = conn.prepareCall(sql);
					ResultSet rs = null;
					ResultSet rs2 = null;
					
					
					
					// set	
					stat.setString(1, openCourseNum); 
					stat.registerOutParameter(2, OracleTypes.CURSOR);
					stat.registerOutParameter(3, OracleTypes.CURSOR);
					stat.executeUpdate();
					
					// get result set
					rs = (ResultSet) stat.getObject(2);
					rs2 = (ResultSet) stat.getObject(3);
					
					// print rs
					System.out.println("[과정명]\t[시작년월일]\t[종료년웡릴]\t[강의실명]\t[교사명]");
					while(rs.next()) {
						System.out.printf("%s\t%s\t%s\t%s\t%s\n",
								rs.getString("ocName"),
								rs.getString("startdate"),
								rs.getString("enddate"),
								rs.getString("classroomnum"),						
								rs.getString("teachername")				
								);
					}
					
					// print rs2
					System.out.println("[구성 과목 리스트]");
					System.out.println("[과목명]\t[시작년월일]\t[종료년월일]\t[교재명]\t");
					while(rs2.next()) {
						System.out.printf("%s\t%s\t%s\t%s\t%s\n",
								rs2.getString("subjectnum"),
								rs2.getString("subjectname"),
								rs2.getString("startdate"),
								rs2.getString("enddate"),
								rs2.getString("bookname")				
								);
					}
					
					// input subject number
					System.out.println("과목 번호:");
					String subjectnum = scan.nextLine();
					
					rs.close();
					rs2.close();
					
					
					System.out.println("시작년월일");
					System.out.print("날짜입력(YY):");
					String startyear = scan.nextLine();
					System.out.print("날짜입력(MM):");
					String startmonth = scan.nextLine();
					System.out.print("날짜입력(DD):");
					String startdate = scan.nextLine();
					
					System.out.println("종료년월일");
					System.out.print("날짜입력(YY):");
					String endyear = scan.nextLine();
					System.out.print("날짜입력(MM):");
					String endmonth = scan.nextLine();
					System.out.print("날짜입력(DD):");
					String enddate = scan.nextLine();
					
					Statement stat2 = conn.createStatement();
					System.out.println("stat2");
					sql = String.format("update tblperiodBySubject set startdate = '%s', enddate = '%s' where num = %s", 
							"20"+startyear + startmonth + startdate,
							"20"+endyear + endmonth + enddate,
							subjectnum
							);
					System.out.println(sql);
					stat2.executeUpdate(sql);
					System.out.println("update");
					// fin message
					System.out.println("수정이 완료되었습니다.");
					
					stat.close();
					stat2.close();
					conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}

	private void PrintSjInfoPeriodBySubject(Connection conn, Scanner scan) {
		
		// print opencourse info
		vwOpencourse(conn);
		
		// input allcoursenum
		System.out.println("과정 번호:");
		String openCourseNum = scan.nextLine();
		
		try {
			
			// declare variable
			String sql = "{ call procPrintSjInfoPeriodBySubject(?,?,?)}";
			CallableStatement stat = conn.prepareCall(sql);
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			
			
			// set	
			stat.setString(1, openCourseNum); 
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			stat.executeUpdate();
			
			// get result set
			rs = (ResultSet) stat.getObject(2);
			rs2 = (ResultSet) stat.getObject(3);
			
			// print rs
			System.out.println("[과정명]\t[시작년월일]\t[종료년웡릴]\t[강의실명]\t[교사명]");
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n",
						rs.getString("ocName"),
						rs.getString("startdate"),
						rs.getString("enddate"),
						rs.getString("classroomnum"),						
						rs.getString("teachername")				
						);
			}
			
			// print rs2
			System.out.println("[구성 과목 리스트]");
			System.out.println("[과목명]\t[시작년월일]\t[종료년월일]\t[교재명]\t");
			while(rs2.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n",
						rs2.getString("subjectnum"),
						rs2.getString("subjectname"),
						rs2.getString("startdate"),
						rs2.getString("enddate"),
						rs2.getString("bookname")				
						);
			}
			
			// fin message
			System.out.println("계속 하시려면 엔터를 입력해주세요.");
			scan.nextLine();
			rs.close();
			rs2.close();
			stat.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void vwOpencourse(Connection conn) {
		
		try {

			// get subject list
			// 과목 리스트 가져오는 sql문
			Statement stat = conn.createStatement();
			String sql = "select * from vwopencourseinfo2";
			ResultSet rs = stat.executeQuery(sql);

			System.out.println("[과정번호]\t[과정명]\t\t[기간]\t[강의실]\n");

			// print subject list
			// 과목 목록 출력
			while (rs.next()) {
				System.out.printf("%s\t%s\t\t%s\t%s\n", 
						rs.getString("opencoursenum"), 
						rs.getString("opencoursename"),
						rs.getString("period"),
						rs.getString("classroomname"));

			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void manageAllcourse(Connection conn, Scanner scan) {

		while(true) {
			
			// Database Connection
			conn = util.open("localhost", "project", "java1234");
			
			System.out.println("〓〓〓〓〓〓〓〓〓 전 체 과 정 관 리〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1. 조회");
			System.out.println("2. 신규 등록");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("0. 뒤로가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력: \n");
			
			String sel = scan.nextLine();
			
			// 조회
			if(sel.equals("1")) {
				// procPrintSubjectList(?,?,?)
				printSubjectList(conn, scan);
			}
			// 등록
			// enroll new Subject
			else if (sel.equals("2")) {
				enrollSubjectListToCourse(conn, scan);
			}
			
			// 수정
			// modify subject info
			else if (sel.equals("3")) {
				modifySubjectListToCourse(conn, scan);
			}
			// 삭제
			// delete subject info
			else if (sel.equals("4")) {
				deleteSubjectListToCourse(conn, scan);
			}
			// exit
			else if(sel.equals("0")) {
				break;
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
			}
		
		}
	}

	private void deleteSubjectListToCourse(Connection conn, Scanner scan) {
		printSubjectList(conn, scan);
		
	}

	private void modifySubjectListToCourse(Connection conn, Scanner scan) {
		printSubjectList(conn, scan);
		
	}

	private void printSubjectList(Connection conn, Scanner scan) {
		
		// print info of allcourse
		vwAllcourse(conn);
		
		// input allcoursenum
		System.out.println("과정 번호:");
		String allCourseNum = scan.nextLine();
		
		try {
			
			// declare variables
			String sql = "{ call procPrintSubjectList(?,?,?)}";
			CallableStatement stat = conn.prepareCall(sql);
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			// set param
			stat.setString(1, allCourseNum); 
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			stat.executeQuery();
			
			// get ResultSet
			// Course name
			rs = (ResultSet) stat.getObject(2);	
			// subject list of the course
			rs2 = (ResultSet) stat.getObject(3);
			
			
			// print rs
			while(rs.next()) {
				System.out.printf("과정명 : %s\n",rs.getString("name"));
			}
			
			// print rs2
			System.out.println("[구성 과목 리스트]");
			int i = 1;
			while(rs2.next()) {
				System.out.printf("%d. %s\n",i,rs2.getString("subjectname"));
				i++;
			}
			
			
			// fin message
			System.out.println("\n계속 하시려면 엔터를 입력해주세요");
			scan.nextLine();
			stat.close();
			conn.close();
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	private void enrollPeriodBySubject(Connection conn, Scanner scan) {
		
		while(true) {
			
			// print list of opencourse
			// 개설과정 출력
			vwOpencourse(conn);
			System.out.println("0. 뒤로가기");
			
			// input courseNum
			System.out.println("과정번호: ");
			String courseNum = scan.nextLine();
			if(courseNum.equals("0")) {break;}
			
			// get the period and number of subject by course
			String period = getPeriodOfOpencourse(conn, courseNum);
			int numOfSubject = period.equals("5.5") ? 8 :  period.equals("6") ?  9 : 10  ;
			 // print list of subject of Opencourse
			 // 선택된  과목 출력
			procSubjectListOfOpenCourse(conn,courseNum);
			System.out.println("0. 뒤로가기");
			
			// input subjectnum, startdate, enddate as number of subject 
//			for(int i=0; i<numOfSubject; i++) {
//				System.out.println("과목번호: ");
//				String subjectNum = scan.nextLine();
//				if(subjectNum.equals("0")) {break;}
//
//				System.out.println("시작년월일: ");
//				String startDate = scan.nextLine();
//				if(startDate.equals("0")) {break;}
//
//				System.out.println("종료년월일: ");
//				String endDate = scan.nextLine();
//				if(endDate.equals("0")) {break;}
//				
//				// insert input period
//				try {
//					String sql = "{ call procEnrollperiodBySubject(?,?,?,?)}";
//					CallableStatement stat = conn.prepareCall(sql);
//
//					// set
//					stat.setString(1, courseNum);
//					stat.setString(2, subjectNum);
//					stat.setString(3, startDate);
//					stat.setString(4, endDate);
//					stat.executeUpdate();
//
//					stat.close();
//					// fin message
//					System.out.println("등록이 완료되었습니다.");
//					
//					
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				
//			}
			getTeacherOfOpencourse(conn, courseNum, period);
		}
		
		// Database connection close
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void procSubjectListOfOpenCourse(Connection conn, String courseNum) {
		
		try {
			
			// get subject list
			// 과목 리스트 가져오는 sql문
			String sql = "{ call procPrintSjListofOpencourse(?,?)}";
			CallableStatement stat = conn.prepareCall(sql);
			stat.setString(1, courseNum);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			
			ResultSet rs = (ResultSet) stat.getObject(2);
			
			
			System.out.println("[과목번호]\t[과목명]\t\t\t[구분]\t[순서]");

			// print subject list
			// 과목 목록 출력
			while (rs.next()) {
				System.out.printf("%s\t%s\t\t\t%s\t%s\n", 
						rs.getString("num"), 
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("seq"));

			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private String getPeriodOfOpencourse(Connection conn, String courseNum) {
		String period = "";
		try {

			// get subject list
			// 과목 리스트 가져오는 sql문
			Statement stat = conn.createStatement();
			String sql = String.format("select period from tblopencourse oc inner join tblallcourse ac on oc.allcoursenum = ac.num where oc.num = %s", courseNum);
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				period = rs.getString("period");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return period;
	}
	
	private void getTeacherOfOpencourse(Connection conn, String courseNum, String period) {
	
		
		try {
			
			// get subject number
			Statement stat = conn.createStatement();
			String sql = String.format("select subjectnum from tblsubjectbycourse where allcoursenum = (select oc.allcoursenum from tblopencourse oc inner join tblallcourse ac on oc.allcoursenum = ac.num where oc.num = %s) order by subjectnum", courseNum);
			ResultSet rs = stat.executeQuery(sql);
			
			// the first 3 subject is mandatory, so we have to pass three times
			// 1,2,4 과목은 필수여서 과목번호 3번을 넘깁니다.
			rs.next();rs.next();rs.next();

			// call procedure by period

			// when period is 5.5
			// 5.5개월 과정
			if(period.equals("5.5")) {
				
				try {
					sql = "{ call procPrintAvailableTeacer55(?,?,?,?,?,?)}";
					CallableStatement stat2 = conn.prepareCall(sql);
					
					// rs is list of subject number, get in the number to param of procPrintAvailableTeacer
					// rs는 과목번호를 담고 있고, procPrintAvailableTeacer 매개변수에 과목번호를 넣어줍니다.
					int i=1;
					while(rs.next()) {
						stat2.setString(i, rs.getString("subjectnum"));
						i++;
					}
					stat2.registerOutParameter(6, OracleTypes.CURSOR);
					stat2.executeQuery();
					ResultSet rs2 = (ResultSet) stat2.getObject(6);
					
					// print teacher info
					if(rs2.next()) {
						System.out.println("[강의가능 교사]");
						System.out.println("[교사번호]\t[교사명]");
						System.out.printf("%s\t%s\n\n",rs2.getString("teachernum"),rs2.getString("teachername"));
					} else if(!rs2.next()) {
						System.out.println("[강의가능 교사]");
						System.out.println("없음");
					}
					
					stat.close();
					stat2.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			// when period is 6
			else if(period.equals("6")) {
				
				try {
					sql = "{ call procPrintAvailableTeacer6(?,?,?,?,?,?,?)}";
					CallableStatement stat2 = conn.prepareCall(sql);
					
					int i=1;
					while(rs.next()) {
						stat2.setString(i, rs.getString("subjectnum"));
						i++;
					}
					
					stat2.registerOutParameter(7, OracleTypes.CURSOR);
					stat2.executeQuery();
					
					ResultSet rs2 = (ResultSet) stat2.getObject(6);
					
					if(rs2.next()) {
						System.out.println("[강의가능 교사]");
						System.out.println("[교사번호]\t[교사명]");
						System.out.printf("%s\t%s\n\n",rs2.getString("teachernum"),rs2.getString("teachername"));
					} 
					else if(!rs2.next()) {
						System.out.println("[강의가능 교사]");
						System.out.println("없음");
					}
					
					stat.close();
					stat2.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			// when period is 7
			else if(period.equals("7")) {
				
				try {
					sql = "{ call procPrintAvailableTeacer7(?,?,?,?,?,?,?,?)}";
					CallableStatement stat2 = conn.prepareCall(sql);
					
					int i=1;
					while(rs.next()) {
						stat2.setString(i, rs.getString("subjectnum"));
						i++;
					}
					
					stat2.registerOutParameter(8, OracleTypes.CURSOR);
					stat2.executeQuery();
					
					ResultSet rs2 = (ResultSet) stat2.getObject(6);
					
					if(rs2.next()) {
						System.out.println("[강의가능 교사]");
						System.out.println("[교사번호]\t[교사명]");
						System.out.printf("%s\t%s\n",rs2.getString("teachernum"),rs2.getString("teachername"));
					} else {
						System.out.println("[강의가능 교사]");
						System.out.println("없음");
					}
					
					stat.close();
					stat2.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	private void enrollSubjectListToCourse(Connection conn, Scanner scan) {
		
		while(true) {
			
		// print list of allcourse
		// 전체과정 출력
		vwAllcourse(conn);
		System.out.println("0. 뒤로가기");
		
		// input courseNum
		System.out.println("과정번호: ");
		String courseNum = scan.nextLine();
		if(courseNum.equals("0")) {break;}
		
		// print list of subject
		// 전체 과목 출력
		viewSubjectList(conn);
		System.out.println("0. 뒤로가기");
		
		// input subjectNum
		System.out.println("과목번호: ");
		String subjectNum = scan.nextLine();
		if(subjectNum.equals("0")) {break;}
		
		System.out.println("순서: ");
		String sequence = scan.nextLine();
		if(sequence.equals("0")) {break;}
		
		System.out.println("기간: ");
		String period = scan.nextLine();
		if(period.equals("0")) {break;}
		
		// needMore : 과정에 대해 과목이 등록되어있는지를 보여줘야하나? 과목 등록여부 출력? 지금까지 입력한 정보 보여주기?
		
		
		// enroll input subject to input course
		// 전체 과정의 구성에 과목 추가
		try {
			
			String sql = "{ call procEnrollSubjectListToCourse(?,?,?,?)}";
			CallableStatement stat = conn.prepareCall(sql);

			// set
			stat.setString(1, courseNum); 
			stat.setString(2, subjectNum);
			stat.setString(3, sequence);
			stat.setString(4, period);
			stat.executeUpdate();

			// fin message
			System.out.println("등록이 완료되었습니다.");
			stat.close();
			conn.close();
			break;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	}

	private void vwAllcourse(Connection conn) {
		try {

			// get subject list
			// 과목 리스트 가져오는 sql문
			Statement stat = conn.createStatement();
			String sql = "select * from vwallcourse";
			ResultSet rs = stat.executeQuery(sql);

			System.out.println("[과정번호]\t[과정명]\t\t[목표]\t[정원]\t[기간]");

			// print subject list
			// 과목 목록 출력
			while (rs.next()) {
				System.out.printf("%s\t%s\t\t%s\t%s\t%s\n", 
						rs.getString("num"), 
						rs.getString("name"),
						rs.getString("purpose"),
						rs.getString("capacity"),
						rs.getString("period"));

			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	

	private void enrollNewSubject(Connection conn, Scanner scan) {
		
		// input subjectName, type
		while(true) {
			
		System.out.println("〓〓〓〓〓〓〓〓〓 신 규 등 록 〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("0. 뒤로가기");
		System.out.println("과목명: ");
		String subjectName = scan.nextLine();
		if(subjectName.equals("0")) {break;}
		System.out.println("구분: ");
		String essentialType = scan.nextLine();
		if(essentialType.equals("0")) {break;}
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		try {
			// call procedure procAddSubjectYes(subjectName, essentialType)
			System.out.println(subjectName);
			System.out.println(essentialType);
			String sql = "{ call procAddSubjectYes(?,?)}";
			CallableStatement stat = conn.prepareCall(sql);

			// set
			stat.setString(1, subjectName);
			stat.setString(2, essentialType);
			stat.executeUpdate();

			// fin message
			System.out.println("신규 등록이 완료되었습니다.");
			
			stat.close();
			conn.close();
			break;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	private void viewSubjectList(Connection conn) {
		try {

			// get subject list
			// 과목 리스트 가져오는 sql문
			Statement stat = conn.createStatement();
			String sql = "select * from VWSUBJECT";
			ResultSet rs = stat.executeQuery(sql);

			System.out.println("[과목번호]\t[과목명]\t\t\t[구분]");

			// print subject list
			// 과목 목록 출력
			while (rs.next()) {
				System.out.printf("%s\t%s\t\t\t%s\n", 
						rs.getString("num"), 
						rs.getString("name"),
						rs.getString("essentialtype"));

			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public AdminSubject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
