package exam;

import java.util.List;

import exam.BoardVO;

public interface IBoardDao {
	public int insertBoard(BoardVO bv);
	
	public boolean getBoard(String writer);
	
	public List<BoardVO> getAllBoardList();
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(String writer);
	
	public List<BoardVO> getSearchBoard(BoardVO bv);
		
	}
	
	


