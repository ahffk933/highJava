package service;

import java.util.List;

import vo.*;

public interface Service {

	List<VO> getData();

	void insertData(VO vo);
}
