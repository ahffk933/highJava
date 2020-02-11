package exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.basic.MemberVO;
import util.DBUtil;

public class BoardMain {

private IBoardService bdService;

public BoardMain() {
	bdService = new BoardServiceImpl();
}
	
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
	 * 프로그램 시작메서드 switch문을 do-while로 돌림
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
	scan.nextLine();
		
		System.out.println();
		System.out.println("검색할 writer >> ");
		String writer = scan.nextLine().trim();  //writer 선언해줌
		
		System.out.println("검색할 title >> ");
		String title = scan.nextLine().trim(); 
		
		
		BoardVO bv = new BoardVO(); 
				
		bv.setBoard_writer(writer);
		bv.setBoard_title(title);
		
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" no\ttitle\twriter\tdate\tcontent");
		System.out.println("--------------------------------------------------");
		
List<BoardVO> bdList = bdService.getSearchBoard(bv);
		
		if(bdList.size() ==0) {
			System.out.println("출력할 회원정보가 없다");
		}else {
			for(BoardVO bd2 : bdList) {
				
				System.out.println(bd2.getBoard_no() +"\t"+ bd2.getBoard_title() +"\t"+ bd2.getBoard_writer() +"\t"+ bd2.getBoard_date() +"\t" + bd2.getBoard_content());
			}
		}
		
		
	}
	
				
		
	
//전체목록 조회 쿼리
	private void displayboardAll() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용 ");
		System.out.println("--------------------------------------------------");
		
		List<BoardVO> bdList = bdService.getAllBoardList();
		
		if(bdList.size() ==0) {
			System.out.println("no data");
		}else {
			for(BoardVO bv : bdList) {
				System.out.println(bv.getBoard_no() + "\t"+ bv.getBoard_title() + "\t"+ bv.getBoard_writer() + "\t"+ bv.getBoard_date() + "\t"+ bv.getBoard_content());
			}
		}
		
	}
		
	
//update 수정
	private void updateboard() {
		System.out.println();
		String writer="";
		/*boolean chk = true;
		
		do {
			System.out.println("수정할 writer를 입력하라 >> ");
			String writer = scan.next();
			
			chk = getBoard(writer);
			
			if(chk == false) {
				System.out.println(writer + "존재하지 않는 writer다");
				System.out.println("다시 입력하라");
			}
		}while(chk == false);*/
		
		System.out.println("수정할 내용을 입력하라");
		System.out.print("새로운 title >> ");
		String title = scan.next();
		
		System.out.print("새로운 writer >> ");
		writer = scan.next();
		
/*		scan.nextLine(); //버퍼 비우기
		System.out.println("새로운 date >> ");
		String date = scan.nextLine();*/
		
		scan.nextLine(); 
		System.out.println("새로운 content >> ");
		String content = scan.next();
		
		BoardVO bv = new BoardVO();   //bv에 저장
		
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		int cnt = bdService.updateBoard(bv);
		if(cnt>0) {
			System.out.println(writer + "회원의 정보 수정 완료");
		}else {
			System.out.println(writer + "회원의 정보 수정 실패");
		}
		
		
	}
//글 삭제 delete
	private void deleteboard() {
		System.out.println();
		System.out.println("삭제할 writer >> ");
		String writer = scan.next();
		
int cnt = bdService.deleteBoard(writer);
		
		if(cnt > 0) {
			System.out.println(writer + "의 게시물 삭제 성공");
		}else {
			System.out.println(writer + "의 게시물 삭제 실패");
		}
		
	}
/*
 insert 추가부분
 */
	private void insertboard() {
		
		System.out.println("title >> ");
		String title = scan.next();
		
		
		System.out.println("writer >> ");
		String writer1 = scan.next();
		
		scan.nextLine();
		System.out.println("content >> ");
		String content = scan.nextLine();
		
		BoardVO bv = new BoardVO();     //bv에 저장~
		bv.setBoard_title(title);
		bv.setBoard_writer(writer1);
		bv.setBoard_content(content);
		
int cnt = bdService.insertBoard(bv);  
		
		if(cnt > 0) {  //0이상이면 성공
			System.out.println(writer1 + "추가작업 성공");
		}else {
			System.out.println(writer1 + "추가작업 실패");
		}
	
	}
	
/*
 getBoard 중복체크에 필요한 메서드
 */
	private boolean getBoard(String writer) {
		boolean chk = false;
		
		chk = bdService.getBoard(writer);
		return chk;
	}
//메인 start
	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	

	}

}
