package banner.model.service;

import banner.model.dao.BannerDao;
import banner.model.vo.Banner;
import declare.model.vo.DBo;

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

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Banner> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Banner> list = bdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int deleteBanner(int bannerNo) {
		Connection conn = getConnection();
		int result = bdao.deleteBanner(conn, bannerNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertBanner(Banner banner) {
		Connection conn = getConnection();
		int result = bdao.insertBanner(conn, banner);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateBanner(Banner banner) {
		Connection conn= getConnection();
		int result = bdao.updateBanner(conn, banner);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public Banner selectOne(int bannerNo) {
		Connection conn = getConnection();
		Banner banner = bdao.selectOne(conn, bannerNo);
		close(conn);
		return banner;
	}

	public int deletecheBanner(int[] checkedNum) {
		Connection conn = getConnection();
		int result = bdao.deletecheBanner(conn, checkedNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<String> selectRfiles(int[] checkedNum) {
		Connection conn = getConnection();
		ArrayList<String> list = bdao.selectRfiles(conn, checkedNum);
		close(conn);
		return list;
	}


}
