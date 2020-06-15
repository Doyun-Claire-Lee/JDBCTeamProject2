package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherCheckRating {

	
	//교사 자신이 자신의 평가 점수 조회 (시작)
	public void procPrintTeacherMyRating(TeacherUser tuser) {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		Statement stat2 = null;
		ResultSet rs2 = null;
				
		try {
			
			
			String sql = "{ call procPrintTeacherMyRating(?,?) }";
			
			conn = util.open("211.63.89.64", "project", "java1234");
			stat = conn.prepareCall(sql);
					
			// 교사번호 get
			stat.setInt(1, tuser.getNum());
			
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
						
			rs = (ResultSet)stat.getObject(2); 
			
			
			String opencoursnum = "";
			String description = "";
			String score = "";
			String ocName = "";
			int i = 1;
			
			// opencoursenum, 과정명 찍기 위해 커서 next()
			rs.next();
			opencoursnum = rs.getString("openCourseNum");
			
			// 과정명 얻어오기
			try {
				stat2 = conn.createStatement();
				sql = String.format("select name from tblallcourse ac inner join tblopencourse oc on ac.num = oc.allcoursenum where oc.num = %s", opencoursnum);		
				
				rs2 = stat2.executeQuery(sql);
				if(rs2.next()) {
					ocName = rs2.getString("name");
					
				}
				rs2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("\t\t\t[시행과정명]");
			System.out.println("\t\t\t" + ocName);
			
			System.out.println("\n\t\t\t[평가항목]\t\t\t\t[점수]");
			// 교사 평가 정보 출력
			while(rs.next()) {
				
				// 과정번호
				
				description = rs.getString("description");
				score = rs.getString("score");
				System.out.printf("\t\t\t" + i + ". " + description);
				System.out.printf("\t\t\t" + score + '점' + "\n");
				i++;
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		
			
		} catch (Exception e) {
			System.out.println("교사 자신이 자신의 평가 점수 조회를 실패했습니다.");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
		scan.nextLine();
		
		
	}//교사 자신이 자신의 평가 점수 조회(끝)  
	
	
}//class
