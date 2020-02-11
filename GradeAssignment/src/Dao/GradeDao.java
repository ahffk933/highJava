package Dao;

import java.util.List;

import GradeVO.vo;

public interface GradeDao {
	List<vo> getData();
	
	void insertData(vo vo);


}
