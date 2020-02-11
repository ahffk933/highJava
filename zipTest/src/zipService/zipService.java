package zipService;

import java.util.List;

import zipVO.zipVO;

public interface zipService {
	public List<zipVO> getDong(String dong);
	
	public List<zipVO> getZip(String zipcode);

}
