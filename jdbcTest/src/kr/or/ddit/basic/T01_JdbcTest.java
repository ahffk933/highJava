package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 	JDBC를 이용한 데이터베이스 처리 순서
 	
 	순서 : JDBC드라이버 로딩 => 해당 DB에 접속 =>
 		  질의(SQL명령을 수행) => 질의결과를 받아서 처리 => 종료(자원반납)
 		  
 	1. JDBC드라이버 로딩(오라클기준)
 	 => JDBC드라이버는 DB를 만든 회사에서 제공
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 
 	2. 접속하기 : 접속이 성공하면 Connection객체가 생성된다
 	 DriverManager.getConnection()메서드를 이용한다
 	 
 	3. 질의 Statement객체 또는 PreparedStatement객체를 이용하여 SQL문장을 실행
 	
 	4. 결과 : 
 		1) SQL문이 select일 경우 -> ResultSet객체가 만들어진다
 			ResultSet객체에는 select한 결과가 저장
 		2) SQL문이 insert, update, delete일 경우 -> 정수값을 반환한다
 			(정수값은 보통 실행에 성공한 레코드 수를 말한다)
 		
 */

public class T01_JdbcTest {
	public static void main(String[] args) {
		// DB작업에 필요한 객체변수 선언
		Connection conn = null;  //커넥션 객체를 받는다 import=> .sql로
		Statement stmt = null;
		ResultSet rs = null;  //쿼리문이 select일 경우 필요
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB에 접속(Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "GOO";
			String password = "java";
			
			//실제로 OracleDriver가 사용되는 부분
			conn = DriverManager.getConnection(url, userId, password);
			
			//3. Statement객체 생성 => Connection객체를 이용
			stmt = conn.createStatement();
			
			//4.SQL문을 Statement객체를 이용해 실행하고 실행결과를 ResultSet객체에 저장한다
			String sql = "select * from lprod"; //실행할 SQL문
			
			//**** SQL문이 select일 경우 executeQuery()(->String)메서드를 사용하고,
			//**** insert, update, delete일 경우 executeUpdate()메서드를 사용  //update는 int형태를 리턴
			rs = stmt.executeQuery(sql);  
			
			//5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용해 차례로 읽어와 처리한다
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
			//rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고
			//그 곳에 자료가 있으면 true, 없으면 false를 반환한다
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				
				// 1) rs.get자료형 이름("컬럼명")				
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_id : " + rs.getString("lprod_gu"));
				System.out.println("lprod_id : " + rs.getString("lprod_nm"));
				System.out.println("-------------------------------------");
				
				/* 2) rs.get자료형 이름(컬럼번호) -> 컬럼번호는 1번부터 시작
				System.out.println("lprod_id : " + rs.getInt(1));
				System.out.println("lprod_id : " + rs.getInt(2));
				System.out.println("lprod_id : " + rs.getInt(3));
				*/
			}
			System.out.println("End");
			
		//에러가 있을 시에 작동	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();  //처음부터 끝까지 에러난 모든 함수를 출력해줌
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			//6. 종료(사용했던 자원을 모두 반납한다)
			if(rs != null) try {rs.close();} catch(SQLException e2) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e2) {}
			if(conn != null) try {conn.close();} catch(SQLException e2) {}
		}
	}

}
