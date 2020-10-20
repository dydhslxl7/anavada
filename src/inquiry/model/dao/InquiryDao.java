package inquiry.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import inquiry.model.vo.Inquiry;
import notice.model.vo.Notice;

public class InquiryDao {
	public InquiryDao() {}

	public int getListCount(Connection conn) {
		int totalList = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from inquiry";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				totalList = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return totalList;
	}
	
	public int getListCount(Connection conn, String user) {
		int totalList = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from inquiry where iq_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalList = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalList;
	}
	
	public int getListCount(Connection conn, String column, String keyword) {
		int totalList = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from inquiry where ";
		
		switch(column) {
		case "t" : query += "iq_title like ?"; break;
		case "c" : query += "iq_content like ?"; break;
		case "w" : query += "iq_id like ?"; break;
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalList = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalList;
	}
	
	public int getListCount(Connection conn, String column, String keyword, String id) {
		int totalList = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from inquiry where ";
		
		switch(column) {
		case "t" : query += "iq_title like ? "; break;
		case "c" : query += "iq_content like ? "; break;
		}
		
		query += "and iq_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalList = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return totalList;
	}

	public ArrayList<Inquiry> selectAll(Connection conn, int currentPage, int limit) {
		ArrayList<Inquiry> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * " 
					 + "from (select rank() over(order by iq_no desc) rnum, iq_no, iq_id, iq_title, iq_content, iq_date, iq_answer, "
					 				+ "iq_original, iq_rename, iq_original2, iq_rename2, iq_original3, iq_rename3, iq_type from inquiry) " 
					 + "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Inquiry inquiry = new Inquiry();
				
				inquiry.setIqNo(rset.getInt("iq_no"));
				inquiry.setIqId(rset.getString("iq_id"));
				inquiry.setIqTitle(rset.getString("iq_title"));
				inquiry.setIqContent(rset.getString("iq_content"));
				inquiry.setIqDate(rset.getDate("iq_date"));
				inquiry.setIqAnswer(rset.getString("iq_answer"));
				inquiry.setIqOriginal(rset.getString("iq_original"));
				inquiry.setIqRename(rset.getString("iq_rename"));
				inquiry.setIqOriginal2(rset.getString("iq_original2"));
				inquiry.setIqRename2(rset.getString("iq_rename2"));
				inquiry.setIqOriginal3(rset.getString("iq_original3"));
				inquiry.setIqRename3(rset.getString("iq_rename3"));
				inquiry.setIqType(rset.getString("iq_type"));
				
				list.add(inquiry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Inquiry> selectAllUser(Connection conn, int currentPage, int limit, String user) {
		ArrayList<Inquiry> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * " 
					 + "from (select rank() over(order by iq_no desc) rnum, iq_no, iq_id, iq_title, iq_content, iq_date, iq_answer, "
					 				+ "iq_original, iq_rename, iq_original2, iq_rename2, iq_original3, iq_rename3, iq_type from inquiry where iq_id = ?) " 
					 + "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Inquiry inquiry = new Inquiry();
				
				inquiry.setIqNo(rset.getInt("iq_no"));
				inquiry.setIqId(rset.getString("iq_id"));
				inquiry.setIqTitle(rset.getString("iq_title"));
				inquiry.setIqContent(rset.getString("iq_content"));
				inquiry.setIqDate(rset.getDate("iq_date"));
				inquiry.setIqAnswer(rset.getString("iq_answer"));
				inquiry.setIqOriginal(rset.getString("iq_original"));
				inquiry.setIqRename(rset.getString("iq_rename"));
				inquiry.setIqOriginal2(rset.getString("iq_original2"));
				inquiry.setIqRename2(rset.getString("iq_rename2"));
				inquiry.setIqOriginal3(rset.getString("iq_original3"));
				inquiry.setIqRename3(rset.getString("iq_rename3"));
				inquiry.setIqType(rset.getString("iq_type"));
				
				list.add(inquiry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Inquiry> searchTCW(Connection conn, String column, String keyword, int currentPage, int limit) {
		ArrayList<Inquiry> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * " 
					 + "from (select rank() over(order by iq_no desc) rnum, iq_no, iq_id, iq_title, iq_content, iq_date, iq_answer, "
	 								+ "iq_original, iq_rename, iq_original2, iq_rename2, iq_original3, iq_rename3, iq_type from inquiry ";
		
		switch(column) {
		case "t" : query += "where iq_title like ?) "; break;
		case "c" : query += "where iq_content like ?) "; break;
		case "w" : query += "where iq_id like ?) "; break;
		}
		query += "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Inquiry inquiry = new Inquiry();
				
				inquiry.setIqNo(rset.getInt("iq_no"));
				inquiry.setIqId(rset.getString("iq_id"));
				inquiry.setIqTitle(rset.getString("iq_title"));
				inquiry.setIqContent(rset.getString("iq_content"));
				inquiry.setIqDate(rset.getDate("iq_date"));
				inquiry.setIqAnswer(rset.getString("iq_answer"));
				inquiry.setIqOriginal(rset.getString("iq_original"));
				inquiry.setIqRename(rset.getString("iq_rename"));
				inquiry.setIqOriginal2(rset.getString("iq_original2"));
				inquiry.setIqRename2(rset.getString("iq_rename2"));
				inquiry.setIqOriginal3(rset.getString("iq_original3"));
				inquiry.setIqRename3(rset.getString("iq_rename3"));
				inquiry.setIqType(rset.getString("iq_type"));
				
				list.add(inquiry);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Inquiry> searchUserTC(Connection conn, String column, String keyword, int currentPage, int limit, String id) {
		ArrayList<Inquiry> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * " 
					 + "from (select rank() over(order by iq_no desc) rnum, iq_no, iq_id, iq_title, iq_content, iq_date, iq_answer, "
	 								+ "iq_original, iq_rename, iq_original2, iq_rename2, iq_original3, iq_rename3, iq_type from inquiry ";
		
		switch(column) {
		case "t" : query += "where iq_title like ? and iq_id = ?) "; break;
		case "c" : query += "where iq_content like ? and iq_id = ?) "; break;
		}
		query += "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, id);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Inquiry inquiry = new Inquiry();
				
				inquiry.setIqNo(rset.getInt("iq_no"));
				inquiry.setIqId(rset.getString("iq_id"));
				inquiry.setIqTitle(rset.getString("iq_title"));
				inquiry.setIqContent(rset.getString("iq_content"));
				inquiry.setIqDate(rset.getDate("iq_date"));
				inquiry.setIqAnswer(rset.getString("iq_answer"));
				inquiry.setIqOriginal(rset.getString("iq_original"));
				inquiry.setIqRename(rset.getString("iq_rename"));
				inquiry.setIqOriginal2(rset.getString("iq_original2"));
				inquiry.setIqRename2(rset.getString("iq_rename2"));
				inquiry.setIqOriginal3(rset.getString("iq_original3"));
				inquiry.setIqRename3(rset.getString("iq_rename3"));
				inquiry.setIqType(rset.getString("iq_type"));
				
				list.add(inquiry);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public Inquiry selectOne(Connection conn, int no) {
		Inquiry inquiry = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from inquiry where iq_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				inquiry = new Inquiry();

				inquiry.setIqNo(rset.getInt("iq_no"));
				inquiry.setIqId(rset.getString("iq_id"));
				inquiry.setIqTitle(rset.getString("iq_title"));
				inquiry.setIqContent(rset.getString("iq_content"));
				inquiry.setIqDate(rset.getDate("iq_date"));
				inquiry.setIqAnswer(rset.getString("iq_answer"));
				inquiry.setIqOriginal(rset.getString("iq_original"));
				inquiry.setIqRename(rset.getString("iq_rename"));
				inquiry.setIqOriginal2(rset.getString("iq_original2"));
				inquiry.setIqRename2(rset.getString("iq_rename2"));
				inquiry.setIqOriginal3(rset.getString("iq_original3"));
				inquiry.setIqRename3(rset.getString("iq_rename3"));
				inquiry.setIqType(rset.getString("iq_type"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return inquiry;
	}

	public int insertInquiry(Connection conn, Inquiry inquiry) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into inquiry values ((select max(iq_no)+1 from inquiry), ?, ?, ?, sysdate, default, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inquiry.getIqId());
			pstmt.setString(2, inquiry.getIqTitle());
			pstmt.setString(3, inquiry.getIqContent());
			pstmt.setString(4, inquiry.getIqOriginal());
			pstmt.setString(5, inquiry.getIqRename());
			pstmt.setString(6, inquiry.getIqOriginal2());
			pstmt.setString(7, inquiry.getIqRename2());
			pstmt.setString(8, inquiry.getIqOriginal3());
			pstmt.setString(9, inquiry.getIqRename3());
			pstmt.setString(10, inquiry.getIqType());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteInquiry(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from inquiry where iq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteInquiry(Connection conn, int no, int rnum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update inquiry ";
		
		switch(rnum) {
		case 1 : query += "set iq_original = null, iq_rename = null "; break;
		case 2 : query += "set iq_original2 = null, iq_rename2 = null "; break;
		case 3 : query += "set iq_original3 = null, iq_rename3 = null "; break;
		}
		
		query += "where iq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectRfiles(Connection conn, int no, int rnum) {
		String rfile = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select ";

		switch(rnum) {
		case 1 : query += "iq_rename "; break;
		case 2 : query += "iq_rename2 "; break;
		case 3 : query += "iq_rename3 "; break;
		}
		
		query += "from inquiry where iq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				rfile = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return rfile;
	}

	public int updateInquiry(Connection conn, Inquiry inquiry) {
		int result = 0;
		PreparedStatement pstmt = null;
		int count = 0;
		String query = "update inquiry set iq_title = ?, iq_content = ?, iq_date = sysdate, ";
					
		if(!inquiry.getIqOriginal().equals("null")) {
			query += "iq_original = ?, iq_rename = ?, ";
			count += 2;
		}
		if(!inquiry.getIqOriginal2().equals("null")) {
			query += "iq_original2 = ?, iq_rename2 = ?, ";
			count += 2;
		}
		if(!inquiry.getIqOriginal3().equals("null")) {
			query += "iq_original3 = ?, iq_rename3 = ?, ";
			count += 2;
		}
		query += "iq_type = ? where iq_no = ?";
		count += 2;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inquiry.getIqTitle());
			pstmt.setString(2, inquiry.getIqContent());
			
			int i = 3;

			if(!inquiry.getIqOriginal().equals("null")) {
				pstmt.setString(i, inquiry.getIqOriginal()); i++;
				pstmt.setString(i, inquiry.getIqRename()); i++;
			}
			if(!inquiry.getIqOriginal2().equals("null")) {
				pstmt.setString(i, inquiry.getIqOriginal2()); i++;
				pstmt.setString(i, inquiry.getIqRename2()); i++;
			}
			if(!inquiry.getIqOriginal3().equals("null")) {
				pstmt.setString(i, inquiry.getIqOriginal3()); i++;
				pstmt.setString(i, inquiry.getIqRename3()); i++;
			}

			pstmt.setString(i, inquiry.getIqType()); i++;
			pstmt.setInt(i, inquiry.getIqNo()); i++;

			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int changeIqAnswer(Connection conn, String change, int iqNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update inquiry set iq_answer = ? where iq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, change);
			pstmt.setInt(2, iqNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
}
