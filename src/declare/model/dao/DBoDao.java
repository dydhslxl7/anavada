package declare.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import declare.model.vo.DBo;

public class DBoDao {
	public DBoDao() {}

	public ArrayList<DBo> selectAll(Connection conn) { // 전체목록보기
		ArrayList<DBo> list = new ArrayList<DBo>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from declare_board order by declare_no desc";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				DBo dbo = new DBo();
				dbo.setDboNo(rset.getInt("declare_no"));
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));
				

				list.add(dbo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public DBo selectOne(Connection conn, int dboNo) { // 상세보기
		DBo dbo = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from declare_board where declare_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dboNo);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				dbo = new DBo();
				dbo.setDboNo(dboNo);
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dbo;
	}

	public int insertDBo(Connection conn, DBo dbo) { // 신고게시판 글 등록
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into declare_board values ((select max(declare_no)+1 from declare_board), ?, ?, sysdate, ?, ?, ?, ?, ?, ?, default, default)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dbo.getDboMid());
			pstmt.setString(2, dbo.getDboTitle());
			pstmt.setString(3, dbo.getDboType());
			pstmt.setString(4, dbo.getDboContent());
			pstmt.setString(5, dbo.getDboOriginal());
			pstmt.setString(6, dbo.getDboRename());
			pstmt.setString(7, dbo.getDboUrl());
			pstmt.setString(8, dbo.getDboBId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public int updateDBo(Connection conn, DBo dbo) {// 신고처리완료 수정
		int result = 0;

		PreparedStatement pstmt = null;

		String query = "update declare_board set declareche = ? where declare_no = ?";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, dbo.getDboChe());
			pstmt.setInt(2, dbo.getDboNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteDBo(Connection conn, int dboNo) { // 신고글 삭제기능
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from declare_board where declare_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dboNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<DBo> searchOne(Connection conn, String keyword) { // 유형검색기능
		ArrayList<DBo> list = new ArrayList<DBo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * form declare_board where declare_type =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();
			while (rset.next()) {
				DBo dbo = new DBo();

				dbo.setDboNo(rset.getInt("declare_no"));
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));

				list.add(dbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from declare_board";
		
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

	public ArrayList<DBo> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<DBo> list = new ArrayList<DBo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select * " + 
				"from (select rank() over(order by declare_no desc) rnum, declare_no, member_id, declare_title, declare_date, declare_type, "+
				"declare_content, declare_original, declare_rename, declare_url, declare_id, declareche, declare_count" + 
				" from  declare_board)  where rnum >= ? and rnum <= ?";

		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				DBo dbo = new DBo();
				dbo.setDboNo(rset.getInt("declare_no"));
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));

				list.add(dbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<DBo> searchTorC(Connection conn, int currentPage, int limit, String keyword, String column) {
		ArrayList<DBo> list = new ArrayList<DBo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rank() over (order by declare_no desc) rnum, declare_no, member_id, declare_title, declare_date, declare_type, "
				+ "declare_content, declare_original, declare_rename, declare_url, declare_id, declareche, declare_count from declare_board ";
		
		if(column.equals("title"))
			query += " where declare_title like ?) where rnum >= ? and rnum <= ?";
		else query += " where declare_content like ?) where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				DBo dbo = new DBo();
				dbo.setDboNo(rset.getInt("declare_no"));
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));

				list.add(dbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn, String column, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from declare_board ";
		
		if(column.equals("title"))
			query += " where declare_title like ? ";
		else query += " where declare_content like ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			
			if(rset.next())
				listCount = rset.getInt(1);
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public int getListCount(Connection conn, String user) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from declare_board where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			
			rset = pstmt.executeQuery();
			if(rset.next()){
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
				e.printStackTrace();	
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<DBo> selectAllUser(Connection conn, int currentPage, int limit, String user) {
		ArrayList<DBo> list = new ArrayList<DBo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select * from (select rank() over(order by declare_no desc) rnum, declare_no, member_id, declare_title, declare_date, declare_type, "+
				"declare_content, declare_original, declare_rename, declare_url, declare_id, declareche, declare_count " + 
				" from  declare_board) where member_id = ? and rnum >= ? and rnum <= ? ";

		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				DBo dbo = new DBo();
				dbo.setDboNo(rset.getInt("declare_no"));
				dbo.setDboMid(rset.getString("member_id"));
				dbo.setDboTitle(rset.getString("declare_title"));
				dbo.setDboDate(rset.getDate("declare_date"));
				dbo.setDboType(rset.getString("declare_type"));
				dbo.setDboContent(rset.getString("declare_content"));
				dbo.setDboOriginal(rset.getString("declare_original"));
				dbo.setDboRename(rset.getString("declare_rename"));
				dbo.setDboUrl(rset.getString("declare_url"));
				dbo.setDboBId(rset.getString("declare_id"));
				dbo.setDboChe(rset.getString("declareche"));
				dbo.setDboCount(rset.getInt("declare_count"));

				list.add(dbo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	

}
