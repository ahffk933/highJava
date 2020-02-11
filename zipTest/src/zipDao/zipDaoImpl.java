package zipDao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import zipVO.zipVO;

public class zipDaoImpl implements zipDao{
private SqlMapClient smc;
	
	private static zipDaoImpl dao;
	
	private zipDaoImpl() {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd;
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2.위에서 읽어온 Reader객체를 이용하여
			//		실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); // Reader객체 닫기
		}catch(IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!!");
			e.printStackTrace();
		}
	}
	
	public static zipDaoImpl getInstance() {
		if(dao == null) {
			dao = new zipDaoImpl();
		}
		return dao;
	}
	
	

	@Override
	public List<zipVO> getDong(String dong) {
		List<zipVO> dlist = new ArrayList<zipVO>();
		try {
			dlist = smc.queryForList("ziptb.getDong", dong); //xml file-> namespace.select->id, where값
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dlist;
	}

	@Override
	public List<zipVO> getZip(String zipcode) {
		List<zipVO> zlist = new ArrayList<zipVO>();
		try {
			zlist = smc.queryForList("ziptb.getZip", zipcode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return zlist;
	}

}
