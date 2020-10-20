package banner.model.service;

import banner.model.dao.BannerDao;
import banner.model.vo.Banner;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;


public class BannerService {
	//DI
	private BannerDao bdao = new BannerDao();
	public BannerService() {} 
	
	public ArrayList<Banner> selectAll(){
		Connection conn = getConnection();
		ArrayList<Banner> list = bdao.selectAll(conn);
		close(conn);
		return list;
	}

}
