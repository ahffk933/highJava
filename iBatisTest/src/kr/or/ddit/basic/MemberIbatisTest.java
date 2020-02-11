package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
	public static void main(String[] args) {
	//	iBatis를 이용하여 DB자료를 처리하는 작업 순서
	//	1. iBatis의 환경설정파일을 읽어와 실헹시킨다
		try {
			//1.1 xml문서 읽어오기
			//설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			//1.2위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); //Reader객체 닫기
			
			//2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행
			
			//2-1. insert작업 연습
			System.out.println("Insert작업 시작");
			
			// 1) 저장할 데이터를 VO에 담는다
			MemberVO mv = new MemberVO();
			mv.setMem_id("d001");
			mv.setMem_name("hanna");
			mv.setMem_tel("821021116066");
			mv.setMem_addr("rochester");
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다
			// 형식) smc.insert("namespace값.id값", 파라미터 객체);
			//		반환값 : 성공하면 null이 반환된다
			Object obj = smc.insert("memberTest.insertMember", mv);
			if(obj == null) {
				System.out.println("inserted");
				
			}else {
				System.out.println("insert failue");
			}
			System.out.println("---------------------------------------------");
			
			
			// 2-2. update작업 연습
			System.out.println("update작업 시작");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMem_id("d001");
			mv2.setMem_name("goo");
			mv2.setMem_tel("17788987702");
			mv2.setMem_addr("fsj");
			
			//update()메서드의 반환값은 성공한 레코드 수 이다
			int cnt = smc.update("memberTest.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("updated");
			}else {
				System.out.println("update failue");
			}
			System.out.println("---------------------------------------------");
			
			
			//2-3. delete 연습
			System.out.println("delete작업 시작");
			
			// delete메서드의 반환값 : 성공한 레코드 수
			
			int cnt2 = smc.delete("memberTest.deleteMember", "d001");
			if(cnt2 >0) {
				System.out.println("deleted");
			}else {
				System.out.println("delete failue");
			}
			System.out.println("---------------------------------------------");
			
			// 2-4. select 연습
			//  1) 응답의 결과가 여러개일 경우
/*			System.out.println("select 연습시작 (결과가 여러개인 경우)");
			List<MemberVO> memList;
			
			//응답결과가 여러개일 경우는 queryForList메서드를 사용한다
			//이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다
			memList = smc.queryForList("memberTest.getMemberAll");
			for(MemberVO memVO : memList) {  //mv라는 이름이 중복되서 memVO로 바꿔준거임 there's no meaning
				System.out.print("ID : " + memVO.getMem_id());
				System.out.print("\t name : " + memVO.getMem_name());
				System.out.print("\t tel : " + memVO.getMem_tel());
				System.out.print("\t addr : " + memVO.getMem_addr());
				System.out.println();
			}
			System.out.println("End");*/
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)");
			
			//응답결과가 1개가 확실할 경우, queryForObject메서드를 사용한다
			MemberVO mv3 = (MemberVO)smc.queryForObject("memberTest.getMember", "d002");
			System.out.print("ID : " + mv3.getMem_id());
			System.out.print("\t name : " + mv3.getMem_name());
			System.out.print("\t tel : " + mv3.getMem_tel());
			System.out.print("\t addr : " + mv3.getMem_addr());
			System.out.println("Done");
		
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
