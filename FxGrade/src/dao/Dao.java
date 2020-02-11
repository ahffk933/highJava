package dao;

import java.util.List;
import vo.VO;


public interface Dao {

	List<VO> getData();

	void insertData(VO vo);
	
}
