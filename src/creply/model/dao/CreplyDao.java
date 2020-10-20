package creply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static common.JDBCTemp.*;
import cboard.model.vo.Cboard;
import creply.model.vo.Creply;

public class CreplyDao {
	public CreplyDao() {}

	public ArrayList<Creply> selectList(Connection conn, int cboardNo) {
		ArrayList<Creply> rlist = new ArrayList<Creply>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from creply where cboard_no = ? and creply_depth = 1 order by creply_no asc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNo);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Creply creply = new Creply();
				
				creply.setCreplyNo(rset.getInt("creply_no"));
				creply.setCbaordNo(cboardNo);
				creply.setMemberId(rset.getString("member_id"));
				creply.setCreplyDate(rset.getDate("creply_date"));
				creply.setCreplyContent(rset.getString("creply_content"));
				creply.setParantReply(rset.getInt("parant_reply"));
				creply.setCreplyDepth(rset.getInt("creply_depth"));
				
				rlist.add(creply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rlist;
	}

	public ArrayList<Creply> selectSubReplyList(Connection conn, int cboardNum) {
		ArrayList<Creply> srlist = new ArrayList<Creply>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from creply where cboard_no = ? and creply_depth = 2 order by creply_no asc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNum);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Creply creply = new Creply();
				
				creply.setCreplyNo(rset.getInt("creply_no"));
				creply.setCbaordNo(cboardNum);
				creply.setMemberId(rset.getString("member_id"));
				creply.setCreplyDate(rset.getDate("creply_date"));
				creply.setCreplyContent(rset.getString("creply_content"));
				creply.setParantReply(rset.getInt("parant_reply"));
				creply.setCreplyDepth(rset.getInt("creply_depth"));
				
				srlist.add(creply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return srlist;
	}
	
	public int getAllReplyListCount(Connection conn, int cboardNum) {
		int replyCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from creply where cboard_no = " + cboardNum;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if (rset.next()) {
				replyCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return replyCount;
	}

	public int insertCreply(Connection conn, Creply creply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into creply values(CREPLY_SEQ.nextval, ?, ?, sysdate, ?, "
				+ (creply.getParantReply() != 0 ? "?, " : "null, ")
				+ (creply.getParantReply() != 0 ? "2" : "1")
				+ ")";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, creply.getCbaordNo());
			pstmt.setString(2, creply.getMemberId());
			pstmt.setString(3, creply.getCreplyContent());
			if (creply.getParantReply() != 0) {
				pstmt.setInt(4, creply.getParantReply());
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteCreply(Connection conn, int creplyNum, int depth) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from creply where creply_no = ? "
				+ (depth == 1 ? "or parant_reply = ?" : "");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, creplyNum);
			if (depth == 1) {
				pstmt.setInt(2, creplyNum);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCreply(Connection conn, int creplyNum, String content) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update creply set creply_content = ? where creply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, creplyNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
