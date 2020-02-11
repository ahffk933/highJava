package exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class jdbc_board0113 {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글작성");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료검색");
		System.out.println("  0. 작업 끝");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertboard();
					break;
				case 2 :  // 자료 삭제
					deleteboard();
					break;
				case 3 :  // 자료 수정
					updateboard();
					break;
				case 4 :  // 전체 자료 출력
					displayboardAll();
					break;
				case 5 :  // 자료검색
					searchboard();
					break;
					
				case 0 :  // 작업 끝
					System.out.println("작업을 마친다");
					break;
				default :
					System.out.println("번호를 잘못 입력했다 다시입력하세요");
			}
		}while(choice!=0);
	}
//검색기능
	private void searchboard() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("검색할 writer >> ");
		String writer = scan.next();    //writer 선언해줌
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = " select * from jdbc_board where board_writer = '" + writer+"'";    // '" "' 주의할것
			
	        stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(no + "\t" + title + "\t" + writer + "\t" + date + "\t" + content );
				
			}
			System.out.println("--------------------------------------------------");
			System.out.println("End");
			
		}catch(SQLException e) {
			System.out.println("failue");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
//전체목록 조회 쿼리
	private void displayboardAll() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용 ");
		System.out.println("--------------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println( no + "\t" + title + "\t" + writer + "\t" + date + "\t" + content );
				
			}
			System.out.println("--------------------------------------------------");
			System.out.println("End");
			
		}catch(SQLException e) {
			System.out.println("failue");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
		
	
//update 수정
	private void updateboard() {
		System.out.println();
		
		boolean chk = true;
		
		do {
			System.out.println("수정할 writer를 입력하라 >> ");
			String writer = scan.next();
			
			chk = getBoard(writer);
			
			if(chk == false) {
				System.out.println(writer + "존재하지 않는 writer다");
				System.out.println("다시 입력하라");
			}
		}while(chk == false);
		
		System.out.println("수정할 내용을 입력하라");
		System.out.print("새로운 title >> ");
		String title = scan.next();
		
		System.out.print("새로운 writer >> ");
		String writer = scan.next();
		
/*		scan.nextLine(); //버퍼 비우기
		System.out.println("새로운 date >> ");
		String date = scan.nextLine();*/
		
		scan.nextLine(); 
		System.out.println("새로운 content >> ");
		String content = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, " 
						+ " board_writer = ?, " 
//						+ " date = SYSDATE, "
						+ " board_content = ? " ;
//						+ " where no = board_seq.nextval ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);			
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("수정완료");
			}else {
				System.out.println("수정 실패");
			}
			
		}catch(SQLException e) {
			System.out.println("정보 수정 실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
	}
//글 삭제 delete
	private void deleteboard() {
		System.out.println();
		System.out.println("삭제할 writer >> ");
		String writer = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(writer + "의 게시물 삭제 성공");
			}else {
				System.out.println(writer + "의 게시물 삭제 실패");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
/*
 insert 추가부분
 */
	private void insertboard() {
/*		boolean chk = false;  //중복체크
		int no =0;
		do {
			System.out.println();
			System.out.println("추가할 회원정보를 입력하라");
			System.out.print("writer >> ");
			String writer = scan.next();
			
			chk = getBoard(writer);
			if(chk) {
				System.out.println("board number가" + writer + "인 회원은 이미 존재한다");
				System.out.println("다시 입력하라");
			}  //true면 계속 반복
			
		}while(chk == true);  //false되는 순간 end
*/		
		System.out.println("title >> ");
		String title = scan.next();
		
		
		System.out.println("writer >> ");
		String writer = scan.nextLine();
		
		scan.nextLine();
		System.out.println("content >> ");
		String content = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = " insert into jdbc_board ( board_no, board_title, board_writer, board_date, board_content ) " 
			+ " values (board_seq.NEXTVAL, ?, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, no);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
//			pstmt.setString(4, date);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(writer + "update 작업 성공");
			}else {
				System.out.println(writer + "update 작업 실패");
			}
		}catch(SQLException e) {
			System.out.println(writer + "update 작업 실패");
			e.printStackTrace();
		}finally {
			disConnect(); //자원반납
		}
		
		
	}

	private void disConnect() {
	//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}
/*
 getBoard 중복체크에 필요한 메서드
 */
	private boolean getBoard(String writer) {
		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = " select count(*) cnt from jdbc_board " + " where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;  //중복된다는 의미
			}
		}catch(SQLException e) {
			e.printStackTrace();
			chk = false;
		}finally {
			disConnect();
		}
		return chk;
	}
//메인
	public static void main(String[] args) {
		jdbc_board0113 board = new jdbc_board0113();
		board.start();
	

	}

}
