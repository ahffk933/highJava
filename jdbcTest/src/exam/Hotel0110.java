package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hotel0110 {
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	private Scanner scan;

	public Hotel0110() {
		scan = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");

			stmt = conn.createStatement();

			String sql = " create table hotel " + " (roomnum NUMBER, guestname VARCHAR2(50)) ";

			stmt.executeQuery(sql);

		} catch (ClassNotFoundException e) {
			System.out.println("failue driver loading");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다)
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ee) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ee) {
				}
		}

	}

	public static void main(String[] args) {
		new Hotel0110().checkmenu();

	}

	public void hotelservice() {
		System.out.println();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println(" 1. 체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.print(" 메뉴선택 >> ");
	}

	// 프로그램을 시작하는 메서드
	public void checkmenu() {
		System.out.println("***********************************************");
		System.out.println(" 호텔 문을 열었습니다.");
		System.out.println("***********************************************");

		while (true) {

			hotelservice(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				checkin(); // 체크인
				break;
			case 2:
				checkout(); // 체크아웃
				break;
			case 3:
				room(); // 객실상태
				break;
			case 4:
				hotelout();
				System.out.println("closed");
				return;
			default:
				System.out.println("wrong value try again");
			} // switch문
		} // while문
	}

	private void hotelout() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");

			stmt = conn.createStatement();
			
			

			String sql = " drop table hotel ";

			stmt.executeQuery(sql);

		} catch (ClassNotFoundException e) {
			System.out.println("failue driver loading");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다)
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ee) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ee) {
				}
		}

	}

	private void checkin() {
		System.out.println("select a room");
		int a = scan.nextInt();

		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속(Connection객체 생성)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");

			// statement객체를 이용한 자료 추가
			stmt = conn.createStatement();
			String sql = " select * from hotel where roomnum = " + a;
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString("guestname") != null) {
					System.out.println("not available choose another one");
					return;
				}
			}

			sql = " insert into hotel " + " (roomnum, guestname) " + " values(?, ?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, a);
			System.out.println("insult your name");
			pstmt.setString(2, scan.next());
			pstmt.executeUpdate();
			System.out.println("thank you for the reservation");

		} catch (ClassNotFoundException e) {
			System.out.println("failue driverloding");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e2) {
				}
		}

	}

	private void checkout() {
		System.out.println("insult roomnumber");
		
		int a = scan.nextInt();
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속(Connection객체 생성)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");

			// statement객체를 이용한 자료 추가
			stmt = conn.createStatement();
			String sql = " select * from hotel where roomnum = " + a;
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				if (rs.getInt("roomnum")== a) {
					sql = " delete from hotel where roomnum = ? ";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, a);

					pstmt.executeUpdate();
					System.out.println("updated");	
					
				}
				}
			System.out.println("no data for checkout");
			
			return;
					
		
		
		} catch (ClassNotFoundException e) {
			System.out.println("failue driverloding");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e2) {
				}
		}

	}


	private void room() {
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속(Connection객체 생성)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "GOO", "java");

			// statement객체를 이용한 자료 추가
			stmt = conn.createStatement();

			String sql = " select * from hotel ";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("reserved room : "+ rs.getInt("roomnum") + "\t " + "name of guest : "+ rs.getString("guestname"));
				
				System.out.println("-------------------------------------");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("failue driverloding");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e2) {
				}
		}

	}
}
