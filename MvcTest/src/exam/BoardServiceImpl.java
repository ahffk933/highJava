package exam;

import java.util.List;


public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}


	@Override
	public boolean getBoard(String no) {
		
		return boardDao.getBoard(no);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
	
		return boardDao.getAllBoardList();
	}

	@Override
	public int updateBoard(BoardVO bv) {

		return boardDao.updateBoard(bv);
	}

	@Override
	public int deleteBoard(String writer) {
		int cnt = boardDao.deleteBoard(writer);
		if(cnt > 0) {
			//관리자에게 메일 발송하기
		}

	
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		
		return boardDao.getSearchBoard(bv);
	}


	@Override
	public int insertBoard(BoardVO bv) {
		
		return boardDao.insertBoard(bv);
	}
	

}
