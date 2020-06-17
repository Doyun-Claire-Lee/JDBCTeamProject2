package com.test.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class AdminBasic {

	public void basicMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t기초데이터 관리");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 과정 관리"); // tblOpencourse
			System.out.println("\t\t\t2. 과목 관리"); // tblSubjectByCourse
			System.out.println("\t\t\t3. 강의실"); // tblClassroom
			System.out.println("\t\t\t4. 교재"); // tblBook
			System.out.println("\t\t\t5. 교육생");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷입력: ");
			String num = sc.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			if (num.equals("1")) {
				while(true) {
				// 1.추가 2.수정 3.삭제
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t\t\t   과정 관리");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t1. 과정 조회");
				System.out.println("\t\t\t2. 과정 추가");
				System.out.println("\t\t\t3. 과정 수정");
				System.out.println("\t\t\t4. 과정 삭제");
				System.out.println("\t\t\t0. 뒤로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t▷입력: ");
				String room = sc.nextLine();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				if(room.equals("1")) {
					System.out.println();
					vwtable("select * from tblallcourse");
				}
				else if (room.equals("2")) {
					procAddAllcourse();
				} else if (room.equals("3")) {
					procUpdateAllcourse();
				} else if (room.equals("4")) {
					procDeleteAllcourse();
				} else if (room.equals("0")) {
					break;
				} else {
					System.out.println("\t\t\t잘못된 번호 입니다.");
				}
			}//while
		}//if
			else if(num.equals("2")) {
				while (true) {
			Connection conn =  new DBUtil().open("211.63.89.64", "project", "java1234");
			// 1.추가 2.수정 3.삭제
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과목 관리");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 과목 조회");
			System.out.println("\t\t\t2. 과목 추가");
			System.out.println("\t\t\t3. 과목 수정");
			System.out.println("\t\t\t4. 과목 삭제");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷입력: ");
			String room = sc.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
			if(room.equals("1")) {
				vwtable("select * from tblsubject");
			}
			else if (room.equals("2")) {
				procAddSubject();
			} else if (room.equals("3")) {
				procUpdateSubject();
			} else if (room.equals("4")) {
				procDeleteSubject();
			} else if (room.equals("0")) {
				break;
			} else {
				System.out.println("\t\t\t잘못된 번호 입니다.");
			}
		}
			} else if (num.equals("3")) {
				while (true) {
					// 강의실 1.추가(insert) 2.수정(update) 3.삭제
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t\t\t강의실 관리");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t1. 강의실 조회");
					System.out.println("\t\t\t2. 강의실 추가");
					System.out.println("\t\t\t3. 강의실 수정");
					System.out.println("\t\t\t4. 강의실 삭제");
					System.out.println("\t\t\t0. 뒤로가기");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t▷입력: ");
					String room = sc.nextLine();
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println();
					if(room.equals("1")) {
						vwtable("select * from tblclassroom");
					}
					else if (room.equals("2")) {
						procAddclassroom();
					} else if (room.equals("3")) {
						procUpdateClassRoom();
					} else if (room.equals("4")) {
						procDeleteClassroom();
					} else if (room.equals("0")) {
						break;
					} else {
						System.out.println("\t\t\t잘못된 번호 입니다.");
					}
				}
			} 
			else if (num.equals("4")) {
				 while(true) {
				// 1.추가 2.수정 3.삭제
				 System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t\t\t   교재 관리");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t1. 교재 조회");
				System.out.println("\t\t\t2. 교재 추가");
				System.out.println("\t\t\t3. 교재 수정");
				System.out.println("\t\t\t4. 교재 삭제");
				System.out.println("\t\t\t0. 뒤로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t▷입력:");
				String room = sc.nextLine();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				
				if(room.equals("1")) {
					vwtable("select * from tblbook");
				}
				else if (room.equals("2")) {
					procAddBook();
				} else if (room.equals("3")) {
					procUpdateBook();
				} else if (room.equals("4")) {
					 procDeleteBook();
				} else if (room.equals("0")) {
					break;
				} else {
					System.out.println("\t\t\t잘못된 번호 입니다.");
				}
				 }
			} else if (num.equals("0")) {
				break;
			
			}

			else if (num.equals("5")) {
				while(true) {
				// 1.추가 2.수정 3.삭제
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t\t\t교육생 관리");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t1. 교육생 조회");
				System.out.println("\t\t\t2. 교육생 추가");
				System.out.println("\t\t\t3. 교육생 수정");
				System.out.println("\t\t\t4. 교육생 삭제");
				System.out.println("\t\t\t0. 뒤로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t▷입력:");
				String room = sc.nextLine();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				
				if(room.equals("1")) {
					vwtable("select * from tblstudent");
				}
				else if (room.equals("2")) {
					// procAddStudent();
				} else if (room.equals("3")) {
					// procUpdateStudent();
				} else if (room.equals("4")) {
					// procDeleteStudent();
				} else if (room.equals("0")) {
					break;
				} else {
					System.out.println("\t\t\t잘못된 번호 입니다.");
				}
				}
			}
			 else if (num.equals("0")) {
				break;
			 } else {
				System.out.println("\t\t\t잘못된 번호입니다.");
				}
			}
		
		}

	public void vwtable(String string) {
		System.out.println("\t\t\t―――――――――――――――――――――――――――――――――――――――――――");
		Connection conn2 = null;
		Statement stat2 = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();
		try {
			conn2 = util.open("211.63.89.64", "project", "java1234");
			stat2 = conn2.createStatement();
			
			String sql2 = string;
			rs2 = stat2.executeQuery(sql2);
		    while (rs2.next()) {
				System.out.printf("\t\t\t%s\t%-20s\t%s\n", rs2.getString(1)
															, rs2.getString(2)
															, rs2.getString(3));

			}
		    System.out.println("\t\t\t―――――――――――――――――――――――――――――――――――――――――――");
		    rs2.close();
			stat2.close();
			conn2.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

	public void procAddStudent() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		try {

			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procAddStudent(?,?,?) }";

			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t학생 정보 추가");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// tblStudent에 (이름, 전화번호, 주민번호) 입력한다.
			System.out.print("\t\t\t이름: ");
			String name = scan.nextLine();
			stat.setString(1, name);

			System.out.print("\t\t\t전화번호: ");
			String tel = scan.nextLine();
			stat.setString(2, tel);

			System.out.print("\t\t\t주민번호: ");
			String ssn = scan.nextLine();
			stat.setString(3, ssn);

			stat.executeUpdate();

			stat.close();
			conn.close();

			System.out.println("\t\t\t학생 정보 추가가 완료 되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\t\t\t학생 정보 추가를 실패 했습니다.");
		}

	}// 학생 정보 추가(끝)

	public void procDeleteStudent() {

		procPrintBasicStudent();

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t학생 정보 삭제");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		try {

			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procDeleteStudent(?) }";

			stat = conn.prepareCall(sql);

			// 삭제할 학생 번호를 입력받는다.
			System.out.print("\t\t\t삭제할 학생번호 입력해주세요: ");
			String num = scan.nextLine();
			stat.setString(1, num);

			stat.executeUpdate();

			stat.close();
			conn.close();

			System.out.println("\t\t\t학생 정보 삭제를 완료했습니다.");

		} catch (Exception e) {
			System.out.println("\t\t\t학생 삭제를 실패했습니다");
		}

	}// 학생 정보 삭제(끝)

	public void procUpdateStudenttest() {

		// 학생 정보 출력
		procPrintBasicStudent();

		// 학생 정보 출력 후 -> 학생 정보 수정할 학생번호 입력

		System.out.println();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t학생 정보 수정");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		String oname = ""; // 원본 이름
		String ossn = ""; // 원본 주민번호
		String otel = ""; // 원본 전화번호
		String oregisterdate = ""; // 원본 최초 등록일(temp)
		String oresultOregisterdate = ""; // 원본 최종 최초 등록일(result)
		String odeletestatus = ""; // 원본 삭제상태

		try {

			while (true) {
				conn = util.open("211.63.89.64", "project", "java1234");

				String sql = "{ call procPrinetBasicStudent(?,?) }";

				stat = conn.prepareCall(sql);

				System.out.print("\t\t\t수정할 학생번호를 입력해주세요: ");

				// 학생번호 입력
				int pnum = scan.nextInt();
				stat.setInt(1, pnum);

				stat.registerOutParameter(2, OracleTypes.CURSOR);

				stat.executeQuery();

				rs = (ResultSet) stat.getObject(2);

				System.out.println();
				System.out.println("\t\t\t[이름]\t[주민번호]\t\t\t[전화번호]\t\t\t[등록일]\t\t\t  [상태]");

				// 학생 정보 출력(이름, 주민번호, 전화번호, 등록일, 상태)
				while (rs.next()) {

					// 학생 정보 출력

					System.out.print("\t\t\t");
					System.out.print(rs.getString("name"));
					System.out.print("\t");
					System.out.print(rs.getString("ssn"));
					System.out.print("\t\t");
					System.out.print(rs.getString("tel"));
					System.out.print("\t\t");
					System.out.print(rs.getString("registerdate"));
					// System.out.print("\t");
					System.out.printf("%10s", rs.getString("deletestatus"));
					System.out.println();

					// 학생 정보 저장
					oname = rs.getString("name"); // 이름
					ossn = rs.getString("ssn"); // 주민번호
					otel = rs.getString("tel"); // 전화번호

					// 등록일
					oregisterdate = (rs.getString("registerdate")).substring(0, 10);
					oresultOregisterdate = oregisterdate.replace("-", "");

					// 상태
					odeletestatus = rs.getString("deletestatus");

				}

				rs.close();

				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t\t수정할 항목 선택");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t1. 학생이름");
				System.out.println("\t\t\t2. 전화번호");
				System.out.println("\t\t\t3. 주민번호");
				System.out.println("\t\t\t4. 최초등록일");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				// 번호 선택
				System.out.print("\t\t\t▷입력:");
				int num1 = scan.nextInt();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				Scanner scan1 = new Scanner(System.in);

				// tblStudent에 내용 수정
				switch (num1) {

				case 1:
					// 학생 이름 수정
					System.out.print("\t\t\t학생이름: ");
					String name = scan1.nextLine();

					// String name = scan.nextLine(); // 이거 오류남. 새로운 Scanner 객체 만들어야됨

					String sql1 = "{ call procUpdateStudent(?,?,?,?,?) }";
					stat = conn.prepareCall(sql1);

					stat.setInt(1, pnum); // 수정할 학생번호
					stat.setString(2, name); // 수정할 이름
					stat.setString(3, otel); // 기존값
					stat.setString(4, ossn); // 기존값
					stat.setString(5, oresultOregisterdate); // 기존값

					stat.executeUpdate();
					stat.close();
					System.out.println("\t\t\t이름 수정 완료되었습니다");
					scan.nextLine();
					break;

				case 2:
					System.out.print("\t\t\t전화번호: ");
					String tel = scan1.nextLine();

					sql1 = "{ call procUpdateStudentTest(?,?,?,?,?) }";
					stat = conn.prepareCall(sql1);

					stat.setInt(1, pnum);
					stat.setString(2, oname);
					stat.setString(3, tel);
					stat.setString(4, ossn);
					stat.setString(5, oresultOregisterdate);

					stat.executeUpdate();
					stat.close();
					System.out.println("\t\t\t전화번호 수정 완료되었습니다");
					scan.nextLine();
					break;

				case 3:
					System.out.print("\t\t\t주민번호: ");
					String ssn = scan1.nextLine();

					sql1 = "{ call procUpdateStudentTest(?,?,?,?,?) }";
					stat = conn.prepareCall(sql1);

					stat.setInt(1, pnum);
					stat.setString(2, oname);
					stat.setString(3, otel);
					stat.setString(4, ssn);
					stat.setString(5, oresultOregisterdate);

					stat.executeUpdate();
					stat.close();
					System.out.println("\t\t\t주민번호 수정 완료되었습니다");
					scan.nextLine();
					break;

				case 4:
					System.out.print("\t\t\t최초등록일 년: "); // ex) 입력: 2020
					String registerdateYear = scan1.nextLine();
					System.out.print("\t\t\t최초등록일 월: "); // ex) 입력: 04
					String registerdateMonth = scan1.nextLine();
					System.out.print("\t\t\t최초등록일 일: "); // ex) 입력: 20
					String registerdateday = scan1.nextLine();

					sql1 = "{ call procUpdateStudentTest(?,?,?,?,?) }";
					stat = conn.prepareCall(sql1);

					stat.setInt(1, pnum);
					stat.setString(2, oname);
					stat.setString(3, otel);
					stat.setString(4, ossn);

					// 2020년 입력 하면 -> 20으로 짤라주는거
					String registerdateYearReal = registerdateYear.substring(2, 4);

					stat.setString(5, registerdateYearReal + registerdateMonth + registerdateday);

					stat.executeUpdate();
					stat.close();
					System.out.println("\t\t\t최초등록일 수정 완료되었습니다");
					scan.nextLine();
					break;

				}// switch

				stat.close();
				conn.close();

				System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요 .");
				scan.nextLine();
				break;
			} // while

		} catch (Exception e) {
			e.printStackTrace(); // 오류코드 출력
			System.out.println("실패");

		}

	}// 학생 정보 수정(끝)

//======================================================================================	

	// 학생 정보 수정에 쓰임.

	// 학생 정보 출력(기본) (시작)
	public void procPrintBasicStudent() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;

		try {

			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procPrintBasicStudent(?) }";

			stat = conn.prepareCall(sql);

			stat.registerOutParameter(1, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(1);

			System.out.println("\t\t\t[번호]\t[이름]\t[주민번호]\t\t\t[전화번호]\t\t\t[등록일]\t\t[상태]");

			int num = 1;

			// 모든 학생 정보 출력(이름, 주민번호, 전화번호, 등록일, 수강신청횟수)
			while (rs.next()) {

				System.out.print("\t\t\t");
				// System.out.printf("%4d", num);
				System.out.printf("%s", rs.getString("num"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.print(rs.getString("ssn"));
				System.out.print("\t\t");
				System.out.print(rs.getString("tel"));
				System.out.print("\t\t");
				System.out.print(rs.getString("registerdate").subSequence(0, 11));
				System.out.print("\t");
				System.out.printf("%3s", rs.getString("deletestatus"));
				System.out.println();

				num++;
			}

			rs.close();
			stat.close();
			conn.close();

			System.out.println();
			System.out.println("\t\t\t학생 정보 출력을 완료 했습니다.");

		} catch (Exception e) {
			System.out.println("\t\t\t학생 정보 출력을 실패 했습니다.");
		}

	}// 학생 정보 출력(기본)(끝)

	private void manageSubject(Connection conn, Scanner scan) {

		while (true) {

			// Database Connection
			DBUtil util = new DBUtil();
			conn = util.open("211.63.89.64", "project", "java1234");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t전체 과목 관리");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 조회");
			System.out.println("\t\t\t2. 신규 등록");
			System.out.println("\t\t\t3. 수정");
			System.out.println("\t\t\t4. 삭제");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t▷ 입력: ");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			String sel = scan.nextLine();

			if (sel.equals("1")) {

				System.out.println("\t\t\t계속하시려면 엔터를 입력해주세요.");
				scan.nextLine();
			}
			// 등록
			// enroll new Subject
			else if (sel.equals("2")) {
				enrollNewSubject(conn, scan);
			}

			// 수정
			// modify subject info
			else if (sel.equals("3")) {
				modifySubjectInfo(conn, scan);
			}
			// 삭제
			// delete subject info
			else if (sel.equals("4")) {
				deleteSubjectInfo(conn, scan);
			}
			// exit
			else if (sel.equals("0")) {
				break;
			} else {
				System.out.println("\t\t\t번호를 다시 입력해주세요.");
			}

		}
	}

	private void deleteSubjectInfo(Connection conn, Scanner scan) {

		// print all of subject list
		// 과목 리스트 출력
		while (true) {

			viewSubjectList(conn);

			// input subjectName, type
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과목 삭제");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t0: 뒤로가기");
			System.out.print("\t\t\t과목번호: ");

			String subjectNum = scan.nextLine();
			if (subjectNum.equals("0")) {
				break;
			}

			System.out.print("\t\t\t삭제하시겠습니까? y/n");
			String yesOrNo = scan.nextLine();
			if (yesOrNo.equals("0")) {
				break;
			}

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// yes
			if (yesOrNo.equals("y")) {
				try {
					String sql = "{ call procdeletesubject(?)}";
					CallableStatement stat = conn.prepareCall(sql);

					// set
					stat.setString(1, subjectNum);
					stat.executeUpdate();

					// fin message
					System.out.println("\t\t\t삭제가 완료되었습니다.");

					stat.close();
					conn.close();
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// no
			else if (yesOrNo.equals("n")) {
				System.out.println("\t\t\t삭제를 취소합니다.");
			}
			// exception
			else {
				System.out.println("\t\t\t다시 입력해주세요.");
			}
		}

	}

	private void modifySubjectInfo(Connection conn, Scanner scan) {

		viewSubjectList(conn);
		while (true) {

			// input subjectName, type
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과목 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t0: 뒤로가기");

			System.out.print("\t\t\t과목번호: ");
			String subjectNum = scan.nextLine();
			if (subjectNum.equals("0")) {
				break;
			}

			System.out.println("\t\t\t과목명: ");
			String subjectName = scan.nextLine();
			if (subjectName.equals("0")) {
				break;
			}

			System.out.println("\t\t\t구분: ");
			String essentialType = scan.nextLine();
			if (essentialType.equals("0")) {
				break;
			}

			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			try {

				String sql = "{ call procUpdateSJTInfo(?,?,?)}";
				CallableStatement stat = conn.prepareCall(sql);

				// set
				stat.setString(1, subjectNum);
				stat.setString(2, subjectName);
				stat.setString(3, essentialType);
				stat.executeUpdate();

				// fin message
				System.out.println("\t\t\t수정이 완료되었습니다.");
				System.out.println();
				stat.close();
				conn.close();
				break;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void enrollNewSubject(Connection conn, Scanner scan) {

		// input subjectName, type
		while (true) {

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t과목 신규 등록");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t0: 뒤로가기");
			System.out.print("\t\t\t과목명: ");
			String subjectName = scan.nextLine();

			if (subjectName.equals("0")) {
				break;
			}
			System.out.print("\t\t\t구분: ");
			String essentialType = scan.nextLine();
			if (essentialType.equals("0")) {
				break;
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			try {
				System.out.println(subjectName);
				System.out.println(essentialType);
				String sql = "{ call procAddSubjectYes(?,?)}";
				CallableStatement stat = conn.prepareCall(sql);

				// set
				stat.setString(1, subjectName);
				stat.setString(2, essentialType);
				stat.executeUpdate();

				// fin message
				System.out.println("\t\t\t신규 등록이 완료되었습니다.");

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

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t전체 과목 조회");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t[과목번호] [과목명]\t\t[구분]");

			// print subject list
			// 과목 목록 출력
			while (rs.next()) {
				System.out.printf("\t\t\t%s\t%-20s\t%s\n", rs.getString("num"), rs.getString("name"),
						rs.getString("essentialtype"));

			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void procOpenCourseUpdate() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			String sql = "{ call procopenCourseUpdate(?,?,?,?,?,?) }";

			conn.setAutoCommit(false);
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과정 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.print("\t\t\t강의 시작 날짜:");
			String start = scan.nextLine();
			System.out.print("\t\t\t강의 끝 날짜:");
			String end = scan.nextLine();
			System.out.print("\t\t\t강의실 번호:");
			int room = scan.nextInt();
			System.out.print("\t\t\t교수 번호:");
			int teacher = scan.nextInt();
			System.out.print("\t\t\t강의 번호:");
			int course = scan.nextInt();
			System.out.print("\t\t\t변경할 강의 번호:");
			int num = scan.nextInt();
			scan.close();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.setString(1, start);

			stat.setString(2, end);

			stat.setInt(3, room);

			stat.setInt(4, teacher);

			stat.setInt(5, course);

			stat.setInt(6, num);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과정 수정 완료!");
				conn.commit();
			} else {
				System.out.println("\t\t\t과정 수정 실패!");
				conn.rollback();
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void procOpenCourseInsert() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			String sql = "{ call procopenCourseInsert(?,?,?,?,?) }";

			conn.setAutoCommit(false);
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과정 추가");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.print("\t\t\t강의 시작 날짜:");
			String start = scan.nextLine();
			System.out.print("\t\t\t강의 끝 날짜:");
			String end = scan.nextLine();
			System.out.print("\t\t\t강의실 번호:");
			int room = scan.nextInt();
			System.out.print("\t\t\t교수 번호:");
			int teacher = scan.nextInt();
			System.out.print("\t\t\t강의 번호:");
			int course = scan.nextInt();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.setString(1, start);

			stat.setString(2, end);

			stat.setInt(3, room);

			stat.setInt(4, teacher);

			stat.setInt(5, course);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과정 개설 완료!");
				conn.commit();
			} else {
				System.out.println("\t\t\t과정 개설 실패!");
				conn.rollback();
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void vwAllattendance() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.createStatement();

			String sql = String.format("select * from vwAllattendance");

			rs = stat.executeQuery(sql); // select -> rs

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t전체 출결 조회");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 정렬 수정안했음..
			while (rs.next()) {
				System.out.printf("이름:%s 과정:%s 입실날짜:%s 퇴실날짜:%s 출결상황:%s \n"

						, rs.getString("stuName"), rs.getString("courseName"), rs.getString("enterT"),
						rs.getString("outT"), rs.getString("status"));
			}

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void procPrintAttendanceStudent() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {

			conn = util.open("211.63.89.64", "project", "java1234");
			String sql = "{ call procPrintAttendanceStudent(?,?,?) }";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t학생별 출결 조회");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.print("\t\t\t학생번호:");
			int stu = scan.nextInt();
			System.out.print("\t\t\t과정번호:");
			int course = scan.nextInt();
			stat.setInt(1, stu);
			stat.setInt(2, course);
			stat.registerOutParameter(3, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(3);

			while (rs.next()) {
				System.out.printf("이름:%s 과정:%s 입실날짜:%s 퇴실날짜:%s 출결상황:%s\n", rs.getString(1), rs.getString(2),
						rs.getDate(3), rs.getDate(4), rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void procPrintAttendanceDate() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t기간별 출결 조회");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.print("\t\t\t날짜입력(YY):");
			String year = scan.nextLine();
			System.out.print("\t\t\t날짜입력(MM):");
			String month = scan.nextLine();
			System.out.print("\t\t\t날짜입력(DD):");
			String date = scan.nextLine();
			String sql = "{call PROCPRINTATTENDANCEDATE(?,?,?,?)}";

			stat = conn.prepareCall(sql);

			stat.setString(1, year);
			stat.setString(2, month);
			stat.setString(3, date);
			stat.registerOutParameter(4, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(4);

			while (rs.next()) {
				System.out.printf("%s-%s-%s-%s-%s\n", rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void procDeleteSubject() {

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t\t과목 정보 삭제");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("localhost", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procdeletesubject(?)}";
			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t삭제할 과목 번호: ");
			int cnum = sc.nextInt();

			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.setInt(1, cnum);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과목 삭제 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\t\t\t실패!");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	}

	public void procDeleteBook() {
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t\t   교재 삭제");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("localhost", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procdeleteclassroom(?,?)}";
			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t삭제할 교재 번호:");
			int cnum = sc.nextInt();

			stat.setInt(2, cnum);
			stat.registerOutParameter(1, OracleTypes.NUMBER);

				stat.executeUpdate();
				int result = stat.getInt(1);

			if (result == 1) {
				System.out.println("\t\t\t교재 삭제 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\t\t\t실패!");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void procDeleteClassroom() {
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t\t강의실 정보 수정");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procdeleteclassroom(?)}";
			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t변경할 강의실 번호: ");
			int cnum = sc.nextInt();

			stat.setInt(1, cnum);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t강의실 삭제 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procDeleteAllcourse() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("localhost", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procdeleteclassroom(?)}";
			stat = conn.prepareCall(sql);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t강의실 삭제");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t삭제할 강의실 번호: ");
			int cnum = sc.nextInt();

			stat.setInt(1, cnum);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t강의실 삭제 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패!");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void procUpdateAllcourse() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procUpdateAllcourse(?,?,?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t과정 정보 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.print("\t\t\t과정명:");
			String cname = sc.nextLine();
			System.out.print("\t\t\t과정목적:");
			String cpurpose = sc.nextLine();
			System.out.print("\t\t\t과정 정원:");
			int capa = sc.nextInt();
			System.out.print("\t\t\t과정 기간:");
			int cterm = sc.nextInt();
			System.out.print("\t\t\t수정할 과정번호:");
			sc.skip("\r\n");
			int cnum = sc.nextInt();

			stat.setInt(1, cnum);
			stat.setString(2, cname);
			stat.setString(3, cpurpose);
			stat.setInt(4, capa);
			stat.setInt(5, cterm);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과정 수정 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procUpdateBook() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procUpdateBook(?,?,?,?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t교재 정보 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t과목번호:");
			int snum = sc.nextInt();
			sc.skip("\r\n");
			System.out.print("\t\t\t교재명:");
			String bname = sc.nextLine();
			System.out.print("\t\t\t저자:");
			String author = sc.nextLine();
			System.out.print("\t\t\t출판사:");
			String publisher = sc.nextLine();
			System.out.print("\t\t\t수정할 교재 번호:");
			int bnum = sc.nextInt();
			sc.skip("\r\n");

			stat.setInt(1, bnum);
			stat.setInt(2, snum);
			stat.setString(3, bname);
			stat.setString(4, author);
			stat.setString(5, publisher);
			stat.registerOutParameter(6, OracleTypes.NUMBER);

			stat.executeUpdate();
			int result = stat.getInt(6);

			if (result == 1) {
				System.out.println("\t\t\t교재 수정 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procUpdateSubject() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procUpdateSubject(?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t과목 정보 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t과목명:");
			String sname = sc.nextLine();
			System.out.print("\t\t\t과목 구분(공통/특수):");
			String stype = sc.nextLine();
			if (!stype.equals("공통") || !stype.equals("특수")) {
				System.out.println("\t\t\t과목구분엔 '공통'이나 '특수'만 입력가능합니다.");
				for (; true;) {
					System.out.print("\t\t\t과목구분(공통/특수):");
					stype = sc.nextLine();
					if (stype.equals("공통") || stype.equals("특수")) {
						break;
					}
				}
			}
			System.out.print("\t\t\t수정할 과목 번호:");
			int snum = sc.nextInt();

			stat.setInt(1, snum);
			stat.setString(2, sname);
			stat.setString(3, stype);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t강의실 수정 완료!");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procUpdateClassRoom() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procUpdateClassroom(?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t강의실 정보 수정");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t강의실명:");
			String cname = sc.nextLine();
			System.out.print("\t\t\t강의실 정원:");
			String capa = sc.nextLine();
			System.out.print("\t\t\t 변경할 강의실 번호:");
			int cnum = sc.nextInt();

			stat.setInt(1, cnum);
			stat.setString(2, cname);
			stat.setString(3, capa);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t강의실 수정 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procAddAllcourse() {

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t\t\t   과정 추가");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);

		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procAllCourse(?,?,?,?)}";
			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t과정명:");
			String cname = sc.nextLine();
			System.out.print("\t\t\t과정 목표:");
			String purpose = sc.nextLine();
			System.out.print("\t\t\t개설 정원:");
			String capa = sc.nextLine();
			System.out.print("\t\t\t과정 기간:");
			String term = sc.nextLine();

			stat.setString(1, cname);
			stat.setString(2, purpose);
			stat.setString(3, capa);
			stat.setString(4, term);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과정 추가 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procAddBook() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procAddBook(?,?,?,?,?)}";
			stat = conn.prepareCall(sql);

			String sql2 = "select * from tblsubject";
			Statement stat2 = null;
			stat2 = conn.createStatement();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   교재 추가");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t과목번호:");
			int snum = sc.nextInt();
			sc.skip("\r\n");
			System.out.print("\t\t\t교재명:");
			String bname = sc.nextLine();
			System.out.print("\t\t\t저자:");
			String author = sc.nextLine();
			System.out.print("\t\t\t출판사:");
			String publisher = sc.nextLine();

			stat.setInt(1, snum);
			stat.setString(2, bname);
			stat.setString(3, author);
			stat.setString(4, publisher);
			stat.registerOutParameter(5, OracleTypes.NUMBER);

				stat.executeUpdate();
				int result = stat.getInt(5); 

			if (result == 1) {
				System.out.println("\t\t\t교재 추가 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procAddSubject() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		boolean chk = true;
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procAddSubject(?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t   과목 추가");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.print("\t\t\t과목명:");
			String sname = sc.nextLine();
			System.out.print("\t\t\t과목구분(공통/특수):");
			String stype = sc.nextLine();
			if (!stype.equals("공통") || !stype.equals("특수")) {
				System.out.println("\t\t\t과목구분엔 '공통'이나 '특수'만 입력가능합니다.");
				for (; true;) {
					System.out.print("\t\t\t과목구분(공통/특수):");
					stype = sc.nextLine();
					if (stype.equals("공통") || stype.equals("특수")) {
						break;
					}
				}
			}

			stat.setString(1, sname);
			stat.setString(2, stype);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t과목 추가 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void procAddclassroom() {

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner sc = new Scanner(System.in);
		try {
			conn = util.open("211.63.89.64", "project", "java1234");
			conn.setAutoCommit(false);

			String sql = "{call procAddClassroom(?,?)}";
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t\t\t강의실 추가");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			System.out.print("\t\t\t강의실명:");
			String rname = sc.nextLine();
			System.out.print("\t\t\t강의실 정원:");
			int rcapa = sc.nextInt();

			stat.setString(1, rname);
			stat.setInt(2, rcapa);

			int result = stat.executeUpdate();

			if (result == 1) {
				System.out.println("\t\t\t강의실 추가 완료");
				conn.commit();
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
