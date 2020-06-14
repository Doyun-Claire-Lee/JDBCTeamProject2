
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex06_PreparedStatement {
	public static void main(String[] args) {
		
		//Ex06_PreparedStatement.java
		//insert 작업 
		//m1(); // Statement	> 정적 쿼리
		m2(); // PreparedStatement > 동적 쿼리 
		
		//Statement vs Prepared Statement 
		//1. SQL 매개변수 유무 
		// select를 그냥 불러올거냐 아니면 조건을 걸어서 동적으로 select를 할거냐에 따라서 달라지는 것?? 질문
		// - select * form tblInsa; (정적쿼리) > Statement
		// - select * from tblInsa where num = ?; (동적쿼리) > PreparedStatement => insert,update,delete
		
		
		
		
		
	}

	private static void m1() {
		
		//사용자 입력
		String name = "아무개";
		String age = "25";
		String tel = "010+1212-1212";
		String address = "서울시 강남구's 역삼동"; // '구분을 못함
		
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open();
			stat = conn.createStatement();

			String sql = String.format("insert into tblAddress (seq,name,age,tel,address,regdate) values (seqAddress.nextVal,'%s',%s,'%s','%s',default)", 
					name,age,tel,address); // 순수 자바
			
			stat.executeUpdate(sql);
			
			System.out.println("실행완료");

			stat.close();
			conn.close();

		} catch (Exception e) {

		}
		
	}
	
	private static void m2() {
		
		//사용자 입력
		String name = "아무개";
		String age = "25";
		String tel = "010-1212-1212";
		String address = "서울시 강남구's 역삼동"; // '구분을 못함
		
		
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			conn = util.open();
			

			String sql = "insert into tblAddress (seq,name,age,tel,address,regdate) values (seqAddress.nextVal,?,?,?,?,default)"; // ? 변수 파라미터 오라클에서
			
			//stat = conn.createStatement(); 실행할때 sql을 씀
			stat = conn.prepareStatement(sql);
			
			//매개변수를 준비해 놓고 실행하는 것
			stat.setString(1, name); // 자바 자료형에 따라서 바뀜, 1번째 물음표에
			stat.setString(2,age); 	// sql을 모르면 물음표의 존재를 인식하고 채워넣을 수 있어
			stat.setString(3, tel);
			stat.setString(4, address); // 자동으로 이스케이프를 처리함
			
			stat.executeUpdate();
			
			System.out.println("실행완료");

			stat.close();
			conn.close();

		} catch (Exception e) {

		}
		
	}

}
