package Dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import GradeVO.vo;
import Service.GradeService;

public class DaoImpl implements GradeDao{
	private static DaoImpl instance;
	private SqlMapClient smc;
	
	private void DaoImpl() {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static GradeDao getInstance() {
		if(instance == null) {
			instance = new DaoImpl();
		}
		return instance;
	}


	@Override
	public void insertData(vo vo) {
		try {
			smc.insert("grade.insertData", vo);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<vo> getData() {
		List<vo> list = null;
		try {
			list = smc.queryForList("grade.getDataList");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

}
