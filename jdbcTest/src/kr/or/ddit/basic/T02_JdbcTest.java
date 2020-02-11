package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_JdbcTest {
	/*
	  문제 1) 사용자로 부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하라
	  
	  문제 2) lprod_id값을 2개 입력받아 두 값 중 작은 값부터 큰값 사이의 자료를 출력하라
	 */
	public static void main(String[] args) {
		Connection conn = null;  //커넥션 객체를 받는다 import=> .sql로
		Statement stmt = null;
		ResultSet rs = null;  //쿼리문이 select일 경우 필요
		
		Scanner scan = new Scanner(System.in);
		//1)
		//System.out.println("lprod_id값 입력 : ");
		//int num = scan.nextInt();
		
		//2)
		System.out.println("lprod_id값 2개 입력 : ");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		scan.close();
		
		int max, min;
		if(num1>num2) {
			max = num1;
			min = num2;
		}else {
			max = num2;
			min = num1;
		}
		/*max = Math.max(num1, num2);
		min = Math.min(num1, num2);*/
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB에 접속(Connection객체 생성)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");
			
			
			//Statement객체 생성 => Connection객체를 이용
			stmt = conn.createStatement();
			
			//SQL문을 Statement객체를 이용해 실행하고 실행결과를 ResultSet객체에 저장한다
			//1
			//String sql = "select * from lprod where lprod_id> " + num;
			//2
			String sql = "select * from lprod "
							+ " where lprod_id >= " + min + " and lprod_id <=" + max;
					
			//**** SQL문이 select일 경우 executeQuery()(->String)메서드를 사용하고,
			//**** insert, update, delete일 경우 executeUpdate()메서드를 사용  //update는 int형태를 리턴
			rs = stmt.executeQuery(sql);  
				
			
			//result객체에 저장된 자료를 출력
			System.out.println("---결과---");
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				
				// 1) rs.get자료형 이름("컬럼명")				
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
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
			System.out.println("failue driver loading");
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			//6. 종료(사용했던 자원을 모두 반납한다)
			if(rs != null) try {rs.close();} catch(SQLException ee) {}
			if(stmt != null) try {stmt.close();} catch(SQLException ee) {}
			if(conn != null) try {conn.close();} catch(SQLException ee) {}
		}

	}

}