package service;

import java.util.List;

import dao.Dao;
import dao.DaoImpl;
import vo.VO;

public class ServiceImpl implements Service {
	private static ServiceImpl instance;
	
	public ServiceImpl() {}
	
	Dao dao = DaoImpl.getInstance();
	
	public static Service getInstance() {
		if(instance == null) {
			instance = new ServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<VO> getData() {
		return dao.getData();
	}

	@Override
	public void insertData(VO vo) {
		dao.insertData(vo);
	} 

}
