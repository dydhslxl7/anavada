package banner.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import banner.model.vo.Banner;
import declare.model.vo.DBo;

public class BannerDao {
	public BannerDao() {}
	
	public ArrayList<Banner> selectAll(Connection conn){
		ArrayList<Banner> list = new ArrayList<Banner>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from Banner";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Banner banner = new Banner();
				
				banner.setBannerNo(rset.getInt("banner_no"));
				banner.setBannerTitle(rset.getString("banner_title"));
				banner.setBannerChk(rset.getString("banner_chk"));
				banner.setBannerContent(rset.getString("banner_content"));
				banner.setBannerOriginal(rset.getString("banner_original"));
				banner.setBannerRename(rset.getString("banner_rename"));
				banner.setBannerSize(rset.getString("banner_size"));
				banner.setBannerUrl(rset.getString("banner_url"));
				
				list.add(banner);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return list;		
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from banner";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
				e.printStackTrace();	
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
		
	}

	public ArrayList<Banner> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Banner> list = new ArrayList<Banner>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rank() over(order by banner_no desc) rnum, banner_no, banner_title, banner_chk, banner_content, banner_original, "
				+ "banner_rename, banner_size, banner_url from  banner)  where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Banner banner = new Banner();

				banner.setBannerNo(rset.getInt("banner_no"));
				banner.setBannerTitle(rset.getString("banner_title"));
				banner.setBannerChk(rset.getString("banner_chk"));
				banner.setBannerContent(rset.getString("banner_content"));
				banner.setBannerOriginal(rset.getString("banner_original"));
				banner.setBannerRename(rset.getString("banner_rename"));
				banner.setBannerSize(rset.getString("banner_size"));
				banner.setBannerUrl(rset.getString("banner_url"));

				list.add(banner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteBanner(Connection conn, int bannerNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from banner where banner_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bannerNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBanner(Connection conn, Banner banner) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into banner values((select max(banner_no)+1 from banner),?,default,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, banner.getBannerTitle());
			pstmt.setString(2, banner.getBannerContent());
			pstmt.setString(3, banner.getBannerOriginal());
			pstmt.setString(4, banner.getBannerRename());
			pstmt.setString(5, banner.getBannerSize());
			pstmt.setString(6, banner.getBannerUrl());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBanner(Connection conn, Banner banner) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update banner set banner_title = ?, banner_chk = ?, "
				+ "banner_content = ?, banner_original = ?, banner_rename = ?, banner_url=?  where banner_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, banner.getBannerTitle());
			pstmt.setString(2, banner.getBannerChk());
			pstmt.setString(3, banner.getBannerContent());
			pstmt.setString(4, banner.getBannerOriginal());
			pstmt.setString(5, banner.getBannerRename());
			pstmt.setString(6, banner.getBannerUrl());
			pstmt.setInt(7, banner.getBannerNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Banner selectOne(Connection conn, int bannerNo) {
		Banner banner = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from banner where banner_no = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bannerNo);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				banner = new Banner();

				banner.setBannerNo(bannerNo);
				banner.setBannerTitle(rset.getString("banner_title"));
				banner.setBannerChk(rset.getString("banner_chk"));
				banner.setBannerContent(rset.getString("banner_content"));
				banner.setBannerOriginal(rset.getString("banner_original"));
				banner.setBannerRename(rset.getString("banner_rename"));
				banner.setBannerSize(rset.getString("banner_size"));
				banner.setBannerUrl(rset.getString("banner_url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}
		return banner;
	}

	public int deletecheBanner(Connection conn, int[] checkedNum) {
		int result = 0;
		Statement stmt = null;
		
		String query = null;
		if(checkedNum.length == 1)
			query = "delete from banner where banner_no = " + checkedNum[0];
		else {
			query = "delete from banner where banner_no in (";
			for(int i=0; i<checkedNum.length-1; i++) {
				query += checkedNum[i]+", ";
			}
			query += checkedNum[checkedNum.length-1]+")";
		}
		try {
			stmt = conn.createStatement();			
			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}

	public ArrayList<String> selectRfiles(Connection conn, int[] checkedNum) {
		ArrayList<String> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "";
		
		if(checkedNum.length == 1) {
			query = "select no_rename from banner where banner_no = " + checkedNum[0];
		}else {
			query = "select no_rename from banner where banner_no in (";
			for(int i=0; i<checkedNum.length-1; i++) {
				query += checkedNum[i] + ", ";
			}
			query += checkedNum[checkedNum.length-1] + ")";
		}
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				String rfile = rset.getString("banner_rename");
				list.add(rfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		
		return list;
	}
		
	
	

}
