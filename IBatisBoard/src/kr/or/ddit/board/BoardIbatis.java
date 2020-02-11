package kr.or.ddit.board;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BoardIbatis {
	Scanner scan = new Scanner(System.in);

	
	
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	//	iBatis를 이용하여 DB자료를 처리하는 작업 순서
	//	1. iBatis의 환경설정파일을 읽어와 실헹시킨다
		try {
			//1.1 xml문서 읽어오기
			//설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");  //한글 불러오는
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");  //rd로 저장
			
			//1.2위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);  //get같은 기능
			
			rd.close(); //Reader객체 닫기
			
			
				int choice;
				do{
					displayMenu(); //메뉴 출력
					choice = scan.nextInt(); // 메뉴번호 입력받기
					switch(choice){
						case 1 :  // 자료 입력
							System.out.println("Insert작업 시작");
							
							// 1) 저장할 데이터를 VO에 담는다
							BoardVO bv = new BoardVO();
					//		bv.setBoard_no("board_no");
							System.out.println("title >> ");
							bv.setBoard_title(scan.next());
							System.out.println("writer >> ");
							bv.setBoard_writer(scan.next());
					//		bv.setBoard_date("board_date");
							System.out.println("content >> ");
							bv.setBoard_content(scan.next());
							
							// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다
							// 형식) smc.insert("namespace값.id값", 파라미터 객체);
							//		반환값 : 성공하면 null이 반환된다
							Object obj = smc.insert("boardTest.insertBoard", bv);
							if(obj == null) {
								System.out.println("inserted");
								
							}else {
								System.out.println("insert failed");
							}
							System.out.println("---------------------------------------------");
							
							break;
						case 2 :  // 자료 삭제

							// 2-2. update작업 연습
							System.out.println("update작업 시작");
							
							BoardVO bv2 = new BoardVO();
							
							
							System.out.println("title >> ");
							bv2.setBoard_title(scan.next());
							System.out.println("writer >> ");
							bv2.setBoard_writer(scan.next());
							System.out.println("content >> ");
							bv2.setBoard_content(scan.next());
							System.out.println("number >> ");
							bv2.setBoard_no(scan.nextInt());
						
							
							//update()메서드의 반환값은 성공한 레코드 수 이다
							int cnt = smc.update("boardTest.updateBoard", bv2);
							
							if(cnt > 0) {
								System.out.println("updated");
							}else {
								System.out.println("update failue");
							}
							System.out.println("---------------------------------------------");
							
					
							break;
						case 3 :  // 자료 수정
							//2-3. delete 연습
							System.out.println("delete작업 시작");
							
							// delete메서드의 반환값 : 성공한 레코드 수
							System.out.println("writer >> ");
							int cnt2 = smc.delete("boardTest.deleteBoard", scan.next());
							if(cnt2 >0) {
								System.out.println("deleted");
							}else {
								System.out.println("delete failue");
							}
							System.out.println("---------------------------------------------");
					
					
							break;
						case 4 :  // 전체 자료 출력
							System.out.println("select시작");
							List<BoardVO> bdList;
							
							//응답결과가 여러개일 경우는 queryForList메서드를 사용한다
							//이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다
							bdList = smc.queryForList("boardTest.getBoardAll");
							
							for(BoardVO bv3 : bdList) {  //mv라는 이름이 중복되서 memVO로 바꿔준거임 there's no meaning
								System.out.println("no : " + bv3.getBoard_no());
								System.out.print("\t title : " + bv3.getBoard_title());
								System.out.print("\t writer : " + bv3.getBoard_writer());
								System.out.println("\t date : " + bv3.getBoard_date());
								System.out.print("\t content : " + bv3.getBoard_content());
								System.out.println();
							}
							break;
						case 5 :  // 자료검색
							// search
							System.out.println("search작업 시작");
							System.out.println("writer >> ");
							
							//응답결과가 1개가 확실할 경우, queryForObject메서드를 사용한다
							BoardVO bv4 = (BoardVO)smc.queryForObject("boardTest.getBoard", scan.next());
							System.out.print("no : " + bv4.getBoard_no());
							System.out.print("\t title : " + bv4.getBoard_title());
							System.out.print("\t writer : " + bv4.getBoard_writer());
							System.out.print("\t date : " + bv4.getBoard_date());
							System.out.println("\t content : " + bv4.getBoard_content());
							System.out.println("---------------------------------------------");
							
						
							break;
							
						case 0 :  // 작업 끝
							System.out.println("작업을 마친다");
							break;
						default :
							System.out.println("번호를 잘못 입력했다 다시입력하세요");
					}
				}while(choice!=0);
	
		}catch(IOException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		
	}

		/**
		 * 메뉴를 출력하는 메서드
		 */
		public static void displayMenu(){
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

	}

