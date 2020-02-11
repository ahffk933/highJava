package exam;

import java.util.List;



public interface IBoardService {
	
	public boolean getBoard(String no);

	
	public List<BoardVO> getAllBoardList();

	/**
	 * 하나의 MemberVO자료를 이용하여 DB를 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO bv);


	public int deleteBoard(String bv);


	public List<BoardVO> getSearchBoard(BoardVO bv);


	public int insertBoard(BoardVO bv);



}
