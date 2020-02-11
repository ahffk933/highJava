package zipService;

import java.util.List;

import zipService.zipService;
import zipVO.zipVO;
import zipDao.zipDaoImpl;

public class zipServiceImpl implements zipService{
	private static zipServiceImpl instance;
	
	private zipServiceImpl() {
		
	}
	
	public static zipServiceImpl getInstance()  {
		if(instance == null) {
			instance = new zipServiceImpl();
		}
		return instance;
	}
	
	zipDaoImpl dao = zipDaoImpl.getInstance();


	@Override
	public List<zipVO>getDong(String dong) {
		
		return dao.getDong(dong);
	}

	@Override
	public List<zipVO>getZip(String zipcode) {
		
		return dao.getZip(zipcode);
	}

}
