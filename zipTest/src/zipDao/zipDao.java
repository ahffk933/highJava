package zipDao;

import java.util.List;

import zipVO.zipVO;

public interface zipDao {
public List<zipVO> getDong(String dong);
	
	public List<zipVO> getZip(String zipcode);


}
