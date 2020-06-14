

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.OracleTypes;

public class test {

	public static void main(String[] args) {
//		update();
//		query();
//		view();
//		procCheckTeaching();
//		procPrintSubjectinfoBycourse();
		procPrintEndSubjectList();
	}



	private static void view() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "select * from vwOpencourseList";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);
			while (rs.next()) { // 결과셋의 레코드만큼 회
				System.out.println(String.format("%s, %s", rs.getString("OPENCOURSENUM"), rs.getString("COURSENAME")));
				;
			}

			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		;
	}

	private static void query() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procPrintConsultContent(?,?,?) }";
			stat = conn.prepareCall(sql);

			stat.setString(1, "1");
			stat.registerOutParameter(2, OracleTypes.DATE);
			stat.registerOutParameter(3, OracleTypes.VARCHAR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);
			rs2 = (ResultSet) stat.getObject(3);

			while (rs.next()) {

				System.out.printf("%s, %s, %s, \n", rs.getString("requestnum"), rs.getString("studentnum"),
						rs.getString("studentname")

				);

			}
			System.out.println();

			rs.close();

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void procPrintSubjectinfoBycourse() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		String opencoursenum = "1";
	
		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			// 1. 교사가 강의중인 과정 정보 출력
			// procPrintOpencourseInfo
			String sql = "{ call procPrintSubjectinfoBycourse(?,?) }";
			stat = conn.prepareCall(sql);
			stat.setInt(1, 1); // opencoursenum
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			rs = (ResultSet) stat.getObject(2);
			
			if (rs.next()) {
				opencoursenum = rs.getString("openCoursenum");
				System.out.println(rs.getString("coursename"));
				System.out.println(rs.getString("startdate"));
				System.out.println(rs.getString("enddate"));
				System.out.println(rs.getString("classroomnum"));
				System.out.println();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	private static void procPrintEndSubjectList() {

		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		CallableStatement stat3 = null;
		CallableStatement stat4 = null;
		CallableStatement stat5 = null;
		CallableStatement stat6 = null;
		CallableStatement stat7 = null;

		CallableStatement tmp1stat = null;
		CallableStatement tmp2stat = null;

		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rS5 = null;

		ResultSet tmp1rs = null;
		ResultSet tmp2rs = null;

		DBUtil util = new DBUtil();

		// 변수 선언
		Scanner scan = new Scanner(System.in);
		int teachernum = 1;
		String opencoursenum = "";
		try {
			conn = util.open("localhost", "project", "java1234");

			// 1. 교사가 강의중인 과정 정보 출력
			// procPrintOpencourseInfo
			String sql = "{ call procPrintOpencourseInfo(?,?) }";
			stat = conn.prepareCall(sql);

			// 교사 로그인 시 해당 교사의 교사번호를 넣는 변수
			stat.setInt(1, teachernum); // 교사번호
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			stat.executeQuery();
			rs = (ResultSet) stat.getObject(2);

			if (rs.next()) {
				opencoursenum = rs.getString("coursenum");
				System.out.println(rs.getString("coursename"));
				System.out.println(rs.getString("startdate"));
				System.out.println(rs.getString("enddate"));
				System.out.println(rs.getString("classroomnum"));
				System.out.println();
			}

			// 2. 해당 과정의 과목 정보를 출력
			// procPrintEndSubjectList

			sql = "{ call procPrintEndSubjectList(?,?) }";
			stat2 = conn.prepareCall(sql);
			stat2.setInt(1, teachernum); // 생성된 교사 객체의 번호
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			stat2.executeQuery();
			rs2 = (ResultSet) stat2.getObject(2);

			// # 성적 등록 여부를 확인
			// procPrintRegitScore
			sql = "{ call procPrintRegitScore(?,?) }";
			tmp1stat = conn.prepareCall(sql);
			tmp1stat.setString(1, opencoursenum);
			tmp1stat.registerOutParameter(2, OracleTypes.CURSOR);
			tmp1stat.executeQuery();
			tmp1rs = (ResultSet) tmp1stat.getObject(2);

			System.out.println(opencoursenum);
			
			// 3. 교사는 자신이 강의를 마친 과목의 목록 보여주기
			while (rs2.next()) {
				// 교사가 강의하는 시험범호의 순서를 넘김
				
				try {
					
					tmp1rs.next();

					// 해당 시험범호에 등록되어있는 학생의 수를 출력
					sql = "{ call procPrintRegitScore2(?,?) }";
					tmp2stat = conn.prepareCall(sql);
					tmp2stat.setString(1, tmp1rs.getString("examnum"));
					tmp2stat.registerOutParameter(2, OracleTypes.NUMBER);
					tmp2stat.executeQuery();

					// 과목의 목록 보여주기
					System.out.printf("%s, %s, %s, %s, %s \n", 
							rs2.getString("subjectnum"), 
							rs2.getString("subjectname"),
							rs2.getString("startdate"), 
							rs2.getString("enddate"), 
							rs2.getString("bookname")

					);
					// 학생 수
					System.out.println("성적이 등록된 사람의 수: " + tmp2stat.getString(2));

					// 필기, 실기, 출결 배점 출력
					sql = "{ call procPrintRatioBySub(?,?,?) }";
					stat3 = conn.prepareCall(sql);
					stat3.setString(1, opencoursenum);
					stat3.setString(2, rs2.getString("subjectnum"));
					stat3.registerOutParameter(3, OracleTypes.CURSOR);
					stat3.executeQuery();
					rs3 = (ResultSet) stat3.getObject(3);

					while (rs3.next()) {
						System.out.printf("%s, %s, %s\n", 
								rs3.getString("wRatio"), 
								rs3.getString("pRatio"),
								rs3.getString("aRatio"));
					}
				} catch (Exception e) {
					break;
				}
				
			}

			// 4. 특정 과목을 선택하면 교육생 정보가 출력되고

			// 사용자에게 특정 과목 선택 받음
//			String subjectNum = scan.nextLine();
			sql = "{ call procPrintEndSubjectList2(?,?) }";
			stat4 = conn.prepareCall(sql);
			stat4.setString(1, opencoursenum);
			stat4.registerOutParameter(2, OracleTypes.CURSOR);
			stat4.executeQuery();
			rs4 = (ResultSet) stat4.getObject(2);

			// 교육생 정보 출력
			while (rs4.next()) {
				System.out.printf("%s, %s, %s \n", 
						rs4.getString("coursehistorynum"), 
						rs4.getString("studentname"),
						rs4.getString("status"));

				// 교육생 성적 출력
				sql = "{ call procPrintScore(?,?) }";
				stat4 = conn.prepareCall(sql);
				stat4.setString(1, rs4.getString("coursehistorynum"));
				stat4.registerOutParameter(2, OracleTypes.CURSOR);
				stat4.executeQuery();
				rS5 = (ResultSet) stat4.getObject(2);
				while (rS5.next()) {
					System.out.printf("%s, %s, %s\n", 
							rS5.getString("wscore"), 
							rS5.getString("pscore"),
							rS5.getString("ascore"));
				}
			}
			
			// ExamPK를 반환
			sql = "{ call procReturnExamPK(?,?,?) }";
			stat6 = conn.prepareCall(sql);
			stat6.setString(1, "1"); // 사용자에게 입력받은 과목 번호가 들어가야함, subjectNum(212 line)
			stat6.setString(2, opencoursenum);
			stat6.registerOutParameter(3, OracleTypes.NUMBER);
			stat6.executeQuery();
			int examPkNum = stat6.getInt(3);

			// 사용자에게 입력할 학생의 번호를 입력받음
			String studentNum = scan.nextLine();
			String wScore = scan.nextLine();
			String pScore = scan.nextLine();
			String aScore = scan.nextLine();

			sql = "{ call procInsertScoreToScorebyExam(?,?,?,?,?) }";
			stat7 = conn.prepareCall(sql);
			stat7.setInt(1,313 ); // studentnum
			stat7.setInt(2,examPkNum ); 
			stat7.setString(3, "100"); // wScore
			stat7.setString(4, "100"); // pScore
			stat7.setString(5, "100"); // aScore
			stat7.executeUpdate();

			
			// 연결 종료
			stat.close();
			stat2.close();
			stat3.close();
			stat4.close();
			stat6.close();
			stat7.close();
			rs.close();
			rs2.close();
			rs3.close();
			rs4.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void procPrintConsultRqListByN() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;

		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procPrintConsultRqListByN(?,?) }";
			stat = conn.prepareCall(sql);

			stat.setString(1, "이채영");
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);
			String requestDate = "";
			int count = 0;

			while (rs.next()) {
				count++;
				if (rs.getString("requestnum") != null) {

					System.out.printf("%s, %s, %s \n",

							rs.getString("requestnum"), rs.getString("studentnum"), rs.getString("studentname"));
				} else {
					System.out.println("상담내역 없음");
				}

				requestDate = rs.getString("requestnum");
				sql = "{ call procPrintConsultContent(?,?,?) }";
				stat2 = conn.prepareCall(sql);
				stat2.setString(1, rs.getString("requestnum"));
				stat2.registerOutParameter(2, OracleTypes.DATE);
				stat2.registerOutParameter(3, OracleTypes.VARCHAR);

				stat2.executeQuery();

				String consultDate = stat2.getString(2); // 상담이 진행되지 않은 경우 신청날짜를 출력
				String consultContent = stat2.getString(3);

				if (consultDate == null) {
					System.out.println("상담 대기중");
				} else {
					System.out.println(consultDate);
				}
				System.out.println(consultContent);

				System.out.println();

			}

			if (count < 1) {
				System.out.println("조회 내역 없음");
			}
			System.out.println();

			rs.close();

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void procPrintConsultRqListByCourse() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;

		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procPrintConsultRqList(?,?) }";
			stat = conn.prepareCall(sql);

			stat.setString(1, "1");
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);
			String requestDate = "";
			while (rs.next()) {

				System.out.printf("%s, %s, %s \n",

						rs.getString("requestnum"), rs.getString("studentnum"), rs.getString("studentname"));
				requestDate = rs.getString("requestnum");
				sql = "{ call procPrintConsultContent(?,?,?) }";
				stat2 = conn.prepareCall(sql);
				stat2.setString(1, rs.getString("requestnum"));
				stat2.registerOutParameter(2, OracleTypes.DATE);
				stat2.registerOutParameter(3, OracleTypes.VARCHAR);

				stat2.executeQuery();

				String consultDate = stat2.getString(2); // 상담이 진행되지 않은 경우 신청날짜를 출력
				String consultContent = stat2.getString(3);

				if (consultDate == null) {
					System.out.println("상담 대기중");
				} else {
					System.out.println(consultDate);
				}
				System.out.println(consultContent);

				System.out.println();

			}
			System.out.println();

			rs.close();

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void procCheckStdInfo() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procCheckStdInfo(?,?) }";
			stat = conn.prepareCall(sql);

			stat.setString(1, "9");
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);

			while (rs.next()) {

				System.out.printf("%s, %s, %s, %s\n", rs.getString("stdName"), rs.getString("stdtel"),
						rs.getString("stdRegitdate"), rs.getString("status"));

			}
			System.out.println();

			rs.close();

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void procCheckTeaching() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("localhost", "project", "java1234");

			String sql = "{ call procCheckTeaching(?,?) }";
			stat = conn.prepareCall(sql);

			// 사용자에게 교사번호 입력받기
			stat.setString(1, "1");

			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);

			while (rs.next()) {

				System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", rs.getString("coursenum"), rs.getString("coursename"),
						rs.getString("classroomnum"), rs.getString("stdNum"), rs.getString("startdate"),
						rs.getString("enddate"), rs.getString("status")

				);

				sql = "{ call procCheckTeaching2(?,?)}";
				stat2 = conn.prepareCall(sql);

				System.out.println();
			}

			// 사용자에게 과정 입력받고 과목 출력
			stat2.setInt(1, 1);
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			stat2.executeQuery();
			rs2 = (ResultSet) stat2.getObject(2);

			while (rs2.next()) {
				System.out.printf("%s, %s, %s, %s, %s\n", rs2.getString("subjectnum"), rs2.getString("subjectname"),
						rs2.getString("sjStardDate"), rs2.getString("sjenddate"), rs2.getString("bookname"));
			}

			System.out.println();

			rs.close();
			rs2.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void update() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open("211.63.89.64", "project", "java1234");

			String sql = "{ call procUpdateSJTInfo(?,?,?) }";
			stat = conn.prepareCall(sql);

			stat.setString(1, "1");
			stat.setString(2, "test");
			stat.setString(3, "test");
//			stat.setString(4, "20201231");

			stat.executeUpdate();
			System.out.println("실행 완료");
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
