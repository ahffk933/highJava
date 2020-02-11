package exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exam.BoardVO;
import kr.or.ddit.basic.MemberVO;
import kr.or.ddit.util.DBUtil3;
import util.DBUtil;

public class BoardDaoImpl implements IBoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private void disConnect() {
		//  사용했던 자원 반납
					if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
					if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
					if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
					if(conn!=null)try{ conn.close(); }catch(SQLException ee){}		
	}


	@Override
	public int insertBoard(BoardVO bv) {
		int cnt =0;
		try {
			conn = DBUtil.getConnection();
			String sql = " insert into jdbc_board ( board_no, board_title, board_writer, board_date, board_content ) " 
			+ " values (board_seq.NEXTVAL, ?, ?, SYSDATE, ?)";     // 입력받을 부분 ?로 
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bv.getBoard_title());      //물음표 순서대로 1, 2, 3 ...
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect(); //자원반납
		}
		return cnt;
	}


	@Override
	public boolean getBoard(String writer) {
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


	@Override
	public List<BoardVO> getAllBoardList() {
    List<BoardVO> bdList = new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getString("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getString("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				bdList.add(bv);
								
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return bdList;
	}


	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, " 
						+ " board_writer = ?, " 
//						+ " date = SYSDATE, "
						+ " board_content = ? " ;
//						+ " where no = board_seq.nextval ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());			
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		
		}catch(SQLException e) {
			System.out.println("정보 수정 실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
		
	}


	@Override
	public int deleteBoard(String writer) {
		int cnt =0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			cnt = pstmt.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return 0;
	}


	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> bdList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from jdbc_board where 1=1 ";
			
			if(bv.getBoard_no() != null && !bv.getBoard_no().equals("")) {
				sql += " and board_no = ? "; 
			}
			if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				sql += " and board_title = ? ";
			}	
			if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				sql += " and board_writer = ? ";
			}
	
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(bv.getBoard_no() != null && !bv.getBoard_no().equals("")) {
				pstmt.setString(index++, bv.getBoard_no());
			}
			if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				pstmt.setString(index++, bv.getBoard_title());
			}	
			if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				pstmt.setString(index++, bv.getBoard_writer());
			}
	
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv1 = new BoardVO();
				bv1.setBoard_no(rs.getString("board_no"));
				bv1.setBoard_title(rs.getString("board_title"));
				bv1.setBoard_writer(rs.getString("board_writer"));
				bv1.setBoard_date(rs.getString("board_date"));
				bv1.setBoard_content(rs.getString("board_content"));
			
				
				bdList.add(bv1);
			}
		}catch(SQLException e) {
			bdList = null;
			e.printStackTrace();
		}finally {
			disConnect();  //자원반납
		}
			
		return bdList;
			
	}
	

}
