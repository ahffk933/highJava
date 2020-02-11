package kr.or.ddit.member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class MemberMain { //열심히하세요
	//Service객체 변수를 선언
	private IMemberService memService;
	
	public MemberMain() {
		memService =  new MemberServiceImpl();
	}
	
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료검색");
		System.out.println("  6. Exit");
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
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 검색자료 출력
					serachMember();
					break;
				case 6 : // end
					System.out.println("작업을 마친다");
					break;
				default :
					System.out.println("번호를 잘못 입력했다 다시입력하세요");
			}
		}while(choice!=6);
	}
	/*
	 회원정보를 삭제하는 메서드(id이용하여 삭제)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원의 ID룰 입력하라 >> ");
		String memId = scan.next();
		
		int cnt = memService.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 삭제 성공");
		}else {
			System.out.println(memId + "회원 삭제 실패");
		}
	
	}

	/*
	 회원정보를 수정하는 메서드
	 */
	private void updateMember() {
		System.out.println();
		String memId="";
		boolean chk = true;
		
		do {
			System.out.println("수정할 회원ID를 입력하라 >> ");
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "존재하지 않는 회원이다");
				System.out.println("다시 입력하라");
			}
		}while(chk == false);
		
		System.out.println("수정할 내용을 입력하라");
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //버퍼 비우기
		System.out.println("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memService.updateMember(mv);
		
		if(cnt>0) {
			System.out.println(memId + "회원정보 수정 완료");
		}else {
			System.out.println(memId + "회원정보 수정 실패");
		}
		
	
	}

	/**
	 전체회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("--------------------------------------------------");
		
		List<MemberVO> memList = memService.getAllMemberList();
		
		if(memList.size() ==0) {
			System.out.println("출력할 회원정보가 없다");
		}else {
			for(MemberVO mv : memList) {
				
				System.out.println(mv.getMem_id() +"\t"+ mv.getMem_name() +"\t"+ mv.getMem_tel() +"\t"+ mv.getMem_addr());
			}
		}
	
	}

	private void insertMember() {
		boolean chk = false;  //중복체크
		String memId="";
		do {
			System.out.println();
			System.out.println("추가할 회원정보를 입력하라");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = getMember(memId);
			if(chk) {
				System.out.println("회원ID가" + memId + "인 회원은 이미 존재한다");
				System.out.println("다시 입력하라");
			}  //true면 계속 반복
			
		}while(chk == true);  //false되는 순간 end
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		//입력받은 정보를 VO객체에 넣는다
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memService.insertMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 추가작업 성공");
		}else {
			System.out.println(memId + "회원 추가작업 실패");
		}
	
		
	}
/**
 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
 * @param memId
 * @return true: 이미 존재함, false:신규회원
 */
	private boolean getMember(String memId) {
		boolean chk = false;
		
		chk = memService.getMember(memId);
	
		return chk;
	}
	
	public void serachMember() {
		//검색할 회원ID, 이름, 번호, 주소 등을 입력하면 입력된 정보만 사용해 검색하는 기능 구현
		//주소는 입력한 값이 포함만 되어도 검색되도록 한다
		//입력하지않을 자료는 엔터키로 다음 입력으로 넘긴다
		scan.nextLine();  //입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하라");
		System.out.println("회원ID >> ");
		String memId = scan.nextLine().trim();
		
		System.out.println("회원 name >> ");
		String memName = scan.nextLine().trim();
		
		System.out.println("회원 telnumber >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.println("회원 address >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("--------------------------------------------------");
		
	
		List<MemberVO> memList = memService.getSearchMember(mv);
		
		if(memList.size() ==0) {
			System.out.println("출력할 회원정보가 없다");
		}else {
			for(MemberVO mv2 : memList) {
				
				System.out.println(mv2.getMem_id() +"\t"+ mv2.getMem_name() +"\t"+ mv2.getMem_tel() +"\t"+ mv2.getMem_addr());
			}
		}
		
		
	}


	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






