
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Ex07_CallableStatement {
	public static void main(String[] args) {
		
		//Ex07_CallableStatement.java
		//1. Statement : 정적 쿼리
		//2. PreparedStatement : 동적 쿼리(매개변수)
		//3. CallableStatement: 프로시저 전용 
		
		//m1();
		//m2();
		//m3(); // 단일 레코드 반환
		//m4();
		m5();
		
	}

	private static void m5() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		try {
			
			String sql = "{call procM5(?)}"; // address 테이블 결과셋이 들어 있어 
			
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1,OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(1);
			
			while(rs.next()) {
				System.out.printf("%s - %s - %s - %s \n"
										, rs.getString("name")
										, rs.getString("age")
										, rs.getString("tel")
										, rs.getString("address"));
				
			}
			rs.close();
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m4()");
			e.printStackTrace();
		}
	}

	private static void m4() {
		
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		try {
			
			String sql = "{call procM4(?,?)}";
			
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1, "기획부");
			stat.registerOutParameter(2, OracleTypes.CURSOR); // out으로 cursor를 반환하는 얘
			
			stat.executeQuery();
			
			//cursor == resultset
			rs = (ResultSet)stat.getObject(2); // 다운캐스팅, 끄집어 낸거를 resultset으로 바꿈 
			
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("jikwi"));
			}
			rs.close();
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m4()");
			e.printStackTrace();
		}
		
	}

	private static void m3() {
		
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		try {
			
			String name = "정형돈";
			
			String sql = "{call procM3(?,?,?)}";
			
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1, name);
			stat.registerOutParameter(2, OracleTypes.VARCHAR);
			stat.registerOutParameter(3,OracleTypes.NUMBER);
			
			stat.executeQuery();
			
			System.out.println(stat.getString(2));
			System.out.println(stat.getString(3));
			
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m3()");
			e.printStackTrace();
		}
	}

	private static void m2() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		try {
			
			String sql = "{call procM2(?,?,?,?)}";
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			//*****
			stat.setString(1, "1001"); // in parameter
			stat.registerOutParameter(2, OracleTypes.VARCHAR); // varchar 타입의 변수를 대입해주세요. out
			stat.registerOutParameter(3, OracleTypes.VARCHAR); 
			stat.registerOutParameter(4, OracleTypes.VARCHAR); 
		
			stat.executeQuery(); // resultset은 안받아
			
			System.out.println(stat.getString(2));
			System.out.println(stat.getString(3));
			System.out.println(stat.getString(4)); // 읽기 작업
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m2()");
			e.printStackTrace();
		}
	}

	private static void m1() {
		
		//PreparedStatement(Text) = CallableStatement(Procedure) 
			
		//매개변수가 있는 프로시저 호출하기
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open();
			
			String sql = "{ call procM1(?,?,?,?) }";
			
			stat = conn.prepareCall(sql);	//sql을 먼저 만들고 setString으로 넣어서 stat.executeUpdate()를 실행 흐름 다시 한번만
			
			stat.setString(1, "호호호");
			//stat.setString(2, "20");
			stat.setInt(2, 20);
			stat.setString(3, "010-9999-9999");
			stat.setString(4, "서울시 강동구 천호동");
			
			stat.executeUpdate();
			
			stat.close();
			conn.close();
			
			System.out.println("완료");
			
		} catch (Exception e) {
			System.out.println("Ex07_CallableStatement.m1()");
			e.printStackTrace();
		}
	}

}
